package com.medisupply.platform.u202218235.procurement.application.internal.commandserviceimpl;

import com.medisupply.platform.u202218235.procurement.application.commands.CreateSupplyRequestCommand;
import com.medisupply.platform.u202218235.procurement.application.commandservices.CreateSupplyRequestCommandService;
import com.medisupply.platform.u202218235.procurement.domain.exceptions.DuplicateRequestCodeException;
import com.medisupply.platform.u202218235.procurement.domain.model.aggregates.SupplyRequest;
import com.medisupply.platform.u202218235.procurement.domain.model.valueobjects.*;
import com.medisupply.platform.u202218235.procurement.domain.repositories.ISupplyRequestRepository;
import com.medisupply.platform.u202218235.shared.domain.model.results.Result;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class CreateSupplyRequestCommandServiceImpl implements CreateSupplyRequestCommandService {
    private final ISupplyRequestRepository supplyRequestRepository;

    public CreateSupplyRequestCommandServiceImpl(ISupplyRequestRepository supplyRequestRepository) {
        this.supplyRequestRepository = supplyRequestRepository;
    }

    @Override
    public Result<SupplyRequest> handle(CreateSupplyRequestCommand command) {
        try {
            var requestCode = new RequestCode(command.requestCode());
            var facilityId = new FacilityId(command.facilityId());
            var fulfillmentPeriod = new FulfillmentPeriod(
                    command.fulfillmentPeriodStartDate(),
                    command.fulfillmentPeriodEndDate()
            );
            var supplyBatch = new SupplyBatch(
                    command.supplyBatchQuantity(),
                    command.supplyBatchUnit()
            );
            var status = SupplyRequestStatus.resolve(command.status(), command.statusPresent());

            if (supplyRequestRepository.existsByRequestCode(requestCode)) {
                return Result.failure(
                        "error.request.code.duplicate",
                        "A supply request with the specified request code already exists",
                        Result.ErrorType.CONFLICT
                );
            }

            var supplyRequest = new SupplyRequest(
                    requestCode,
                    facilityId,
                    fulfillmentPeriod,
                    supplyBatch,
                    command.urgencyLevel(),
                    status,
                    command.requestNotes()
            );

            var savedSupplyRequest = supplyRequestRepository.save(supplyRequest);
            return Result.success(savedSupplyRequest);
        } catch (ValidationException ex) {
            return Result.failure(ex.getErrorCode(), ex.getMessage(), Result.ErrorType.VALIDATION_ERROR);
        } catch (IllegalArgumentException ex) {
            return Result.failure("error.request.body.malformed", ex.getMessage(), Result.ErrorType.VALIDATION_ERROR);
        } catch (DuplicateRequestCodeException ex) {
            return Result.failure(ex.getErrorCode(), ex.getMessage(), Result.ErrorType.CONFLICT);
        } catch (DataIntegrityViolationException ex) {
            return Result.failure(
                    "error.request.code.duplicate",
                    "A supply request with the specified request code already exists",
                    Result.ErrorType.CONFLICT
            );
        } catch (Exception ex) {
            return Result.failure(
                    "error.internal.unexpected",
                    "An unexpected internal error occurred",
                    Result.ErrorType.INTERNAL_ERROR
            );
        }
    }
}