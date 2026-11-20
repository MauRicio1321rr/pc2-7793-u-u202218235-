package com.medisupply.platform.u202218235.shared.domain.model.valueobjects;

import com.medisupply.platform.u202218235.shared.domain.exceptions.ValidationException;

import java.time.LocalDate;

public record FulfillmentPeriod(LocalDate startDate, LocalDate endDate) {
    public FulfillmentPeriod {
        if (startDate == null) {
            throw new ValidationException("error.fulfillment.period.start.null", "Fulfillment period start date cannot be null");
        }
        if (endDate == null) {
            throw new ValidationException("error.fulfillment.period.end.null", "Fulfillment period end date cannot be null");
        }
        if (!endDate.isAfter(startDate)) {
            throw new ValidationException("error.fulfillment.period.end.before.start", "Fulfillment period end date must be after start date");
        }
    }

    public boolean contains(LocalDate checkDate) {
        if (checkDate == null) {
            throw new ValidationException("error.fulfillment.period.check.date.null", "Check date cannot be null");
        }
        return !checkDate.isBefore(startDate) && !checkDate.isAfter(endDate);
    }
}