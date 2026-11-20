package com.medisupply.platform.u202218235.procurement.domain.exceptions;

public class DuplicateRequestCodeException extends RuntimeException {
    private final String errorCode;

    public DuplicateRequestCodeException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}