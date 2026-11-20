package com.medisupply.platform.u202218235.procurement.interfaces.rest.assemblers;

import com.medisupply.platform.u202218235.procurement.application.commands.CreateSupplyRequestCommand;
import com.medisupply.platform.u202218235.procurement.interfaces.rest.resources.CreateSupplyRequestResource;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.UUID;

public class CreateSupplyRequestCommandFromResourceAssembler {

    public static CreateSupplyRequestCommand toCommandFromResource(CreateSupplyRequestResource resource) {
        if (resource == null) {
            throw new ValidationException("error.request.body.null", "Request body cannot be null");
        }
        UUID parsedFacilityId = parseFacilityId(resource.getFacilityId());
        LocalDate parsedStartDate = parseDate(resource.getFulfillmentPeriodStartDate(), "error.fulfillment.period.start.null", "Fulfillment period start date cannot be null");
        LocalDate parsedEndDate = parseDate(resource.getFulfillmentPeriodEndDate(), "error.fulfillment.period.end.null", "Fulfillment period end date cannot be null");

        return new CreateSupplyRequestCommand(
                resource.getRequestCode(),
                parsedFacilityId,
                parsedStartDate,
                parsedEndDate,
                resource.getSupplyBatchQuantity(),
                resource.getSupplyBatchUnit(),
                resource.getUrgencyLevel(),
                resource.getStatus(),
                resource.isStatusPresent(),
                resource.getRequestNotes()
        );
    }

    private static UUID parseFacilityId(String facilityId) {
        if (facilityId == null || facilityId.isBlank()) {
            throw new ValidationException("error.facility.id.null", "Facility ID cannot be null");
        }
        try {
            return UUID.fromString(facilityId.trim());
        } catch (IllegalArgumentException ex) {
            throw new ValidationException("error.facility.id.invalid", "Facility ID must be a valid UUID");
        }
    }

    private static LocalDate parseDate(String dateValue, String nullErrorCode, String nullMessage) {
        if (dateValue == null || dateValue.isBlank()) {
            throw new ValidationException(nullErrorCode, nullMessage);
        }
        try {
            return LocalDate.parse(dateValue.trim(), DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (DateTimeParseException ex) {
            throw new ValidationException("error.fulfillment.period.date.format", "Fulfillment period dates must follow ISO 8601 format (yyyy-MM-dd)");
        }
    }
}