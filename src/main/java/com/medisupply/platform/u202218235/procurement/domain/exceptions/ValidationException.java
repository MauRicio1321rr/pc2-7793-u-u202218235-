package com.medisupply.platform.u202218235.procurement.domain.exceptions;

public class ValidationException extends IllegalArgumentException {
    private final String errorCode;
    public ValidationException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
