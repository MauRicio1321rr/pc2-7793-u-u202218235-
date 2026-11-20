package com.medisupply.platform.u202218235.procurement.infrastructure.assemblers;

import com.medisupply.platform.u202218235.procurement.domain.model.aggregates.SupplyRequest;
import com.medisupply.platform.u202218235.procurement.domain.model.valueobjects.FacilityId;
import com.medisupply.platform.u202218235.procurement.domain.model.valueobjects.FulfillmentPeriod;
import com.medisupply.platform.u202218235.procurement.domain.model.valueobjects.RequestCode;
import com.medisupply.platform.u202218235.procurement.domain.model.valueobjects.SupplyBatch;
import com.medisupply.platform.u202218235.procurement.infrastructure.embeddables.FulfillmentPeriodPersistenceEmbeddable;
import com.medisupply.platform.u202218235.procurement.infrastructure.embeddables.SupplyBatchPersistenceEmbeddable;
import com.medisupply.platform.u202218235.procurement.infrastructure.entities.SupplyRequestPersistenceEntity;

public class SupplyRequestPersistenceAssembler {

    public static SupplyRequest toDomain(SupplyRequestPersistenceEntity entity) {
        if (entity == null) {
            return null;
        }
        return new SupplyRequest(
                entity.getId(),
                new RequestCode(entity.getRequestCode().code()),
                new FacilityId(entity.getFacilityId().value()),
                new FulfillmentPeriod(
                        entity.getFulfillmentPeriod().getStartDate(),
                        entity.getFulfillmentPeriod().getEndDate()
                ),
                new SupplyBatch(
                        entity.getSupplyBatch().getQuantity(),
                        entity.getSupplyBatch().getUnit()
                ),
                entity.getUrgencyLevel(),
                entity.getStatus(),
                entity.getRequestNotes(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    public static SupplyRequestPersistenceEntity toEntity(SupplyRequest supplyRequest) {
        if (supplyRequest == null) {
            return null;
        }
        return SupplyRequestPersistenceEntity.builder()
                .requestCode(new RequestCode(supplyRequest.getRequestCode().code()))
                .facilityId(new FacilityId(supplyRequest.getFacilityId().value()))
                .fulfillmentPeriod(new FulfillmentPeriodPersistenceEmbeddable(
                        supplyRequest.getFulfillmentPeriod().startDate(),
                        supplyRequest.getFulfillmentPeriod().endDate()
                ))
                .supplyBatch(new SupplyBatchPersistenceEmbeddable(
                        supplyRequest.getSupplyBatch().quantity(),
                        supplyRequest.getSupplyBatch().unit()
                ))
                .urgencyLevel(supplyRequest.getUrgencyLevel())
                .status(supplyRequest.getStatus())
                .requestNotes(supplyRequest.getRequestNotes())
                .build();
    }
}