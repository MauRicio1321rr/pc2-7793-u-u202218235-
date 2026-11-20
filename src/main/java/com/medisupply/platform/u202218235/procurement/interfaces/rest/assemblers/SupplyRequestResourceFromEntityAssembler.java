package com.medisupply.platform.u202218235.procurement.interfaces.rest.assemblers;

import com.medisupply.platform.u202218235.procurement.domain.model.aggregates.SupplyRequest;
import com.medisupply.platform.u202218235.procurement.interfaces.rest.resources.SupplyRequestResource;

import java.time.format.DateTimeFormatter;

public class SupplyRequestResourceFromEntityAssembler {

    public static SupplyRequestResource toResourceFromEntity(SupplyRequest entity) {
        if (entity == null) {
            return null;
        }
        return new SupplyRequestResource(
                entity.getId(),
                entity.getRequestCode().code(),
                entity.getFacilityId().value().toString(),
                entity.getFulfillmentPeriod().startDate().format(DateTimeFormatter.ISO_LOCAL_DATE),
                entity.getFulfillmentPeriod().endDate().format(DateTimeFormatter.ISO_LOCAL_DATE),
                entity.getSupplyBatch().quantity(),
                entity.getSupplyBatch().unit(),
                entity.getUrgencyLevel(),
                entity.getStatus().name(),
                entity.getRequestNotes()
        );
    }
}