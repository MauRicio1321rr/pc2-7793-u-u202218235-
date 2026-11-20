package com.medisupply.platform.u202218235.shared.domain.exceptions;

public class ValidationException extends IllegalArgumentException {
    private final String errorCode;
    public ValidationException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
