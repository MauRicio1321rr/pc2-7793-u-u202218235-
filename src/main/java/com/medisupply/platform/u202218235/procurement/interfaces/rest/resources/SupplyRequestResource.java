package com.medisupply.platform.u202218235.procurement.interfaces.rest.resources;

public record SupplyRequestResource(
        Long id,
        String requestCode,
        String facilityId,
        String fulfillmentPeriodStartDate,
        String fulfillmentPeriodEndDate,
        Integer supplyBatchQuantity,
        String supplyBatchUnit,
        Integer urgencyLevel,
        String status,
        String requestNotes
) {
}