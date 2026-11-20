package com.medisupply.platform.u202218235.shared.domain.model.valueobjects;

import com.medisupply.platform.u202218235.shared.domain.exceptions.ValidationException;

public enum SupplyRequestStatus {
    PENDING,
    APPROVED,
    FULFILLED,
    CANCELLED;

    public static SupplyRequestStatus resolve(String status, boolean statusPresent) {
        if (!statusPresent) {
            return PENDING;
        }
        if (status == null || status.isBlank()) {
            throw new ValidationException("error.status.null", "Supply request status cannot be null");
        }
        try {
            return SupplyRequestStatus.valueOf(status.trim());
        } catch (IllegalArgumentException ex) {
            throw new ValidationException("error.status.invalid", "Supply request status must be one of PENDING, APPROVED, FULFILLED, or CANCELLED");
        }
    }
}