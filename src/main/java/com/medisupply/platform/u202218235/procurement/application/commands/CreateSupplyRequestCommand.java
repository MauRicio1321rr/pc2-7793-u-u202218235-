package com.medisupply.platform.u202218235.procurement.application.commands;

import com.medisupply.platform.u202218235.shared.domain.exceptions.ValidationException;

import java.time.LocalDate;
import java.util.UUID;

public record CreateSupplyRequestCommand(
        String requestCode,
        UUID facilityId,
        LocalDate fulfillmentPeriodStartDate,
        LocalDate fulfillmentPeriodEndDate,
        Integer supplyBatchQuantity,
        String supplyBatchUnit,
        Integer urgencyLevel,
        String status,
        boolean statusPresent,
        String requestNotes
) {
    public CreateSupplyRequestCommand {
        if (requestCode == null || requestCode.isBlank()) {
            throw new ValidationException("error.request.code.invalid", "Request code cannot be null or blank");
        }
        if (facilityId == null) {
            throw new ValidationException("error.facility.id.null", "Facility ID cannot be null");
        }
        if (fulfillmentPeriodStartDate == null) {
            throw new ValidationException("error.fulfillment.period.start.null", "Fulfillment period start date cannot be null");
        }
        if (fulfillmentPeriodEndDate == null) {
            throw new ValidationException("error.fulfillment.period.end.null", "Fulfillment period end date cannot be null");
        }
        if (supplyBatchQuantity == null || supplyBatchQuantity <= 0) {
            throw new ValidationException("error.supply.batch.quantity.invalid", "Supply batch quantity must be greater than zero");
        }
        if (supplyBatchUnit == null || supplyBatchUnit.isBlank()) {
            throw new ValidationException("error.supply.batch.unit.invalid", "Supply batch unit cannot be null or blank");
        }
        if (urgencyLevel == null || urgencyLevel < 1 || urgencyLevel > 5) {
            throw new ValidationException("error.urgency.level.invalid", "Urgency level must be an integer between 1 and 5");
        }
    }
}