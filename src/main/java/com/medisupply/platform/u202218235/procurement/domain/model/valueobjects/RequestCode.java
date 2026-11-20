package com.medisupply.platform.u202218235.procurement.domain.model.valueobjects;

import com.medisupply.platform.u202218235.procurement.domain.exceptions.ValidationException;

public record RequestCode(String code) {
    public RequestCode {
        if (code == null || code.isBlank()) {
            throw new ValidationException("error.request.code.invalid", "Request code cannot be null or blank");
        }
    }
}