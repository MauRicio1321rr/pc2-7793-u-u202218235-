package com.medisupply.platform.u202218235.shared.domain.model.results;


public sealed interface Result<T> permits Result.Success, Result.Failure {

    record Success<T>(T value) implements Result<T> {
    }

    record Failure<T>(String errorCode, String message, ErrorType errorType) implements Result<T> {
    }

    enum ErrorType {
        VALIDATION_ERROR,
        CONFLICT,
        INTERNAL_ERROR
    }

    static <T> Result<T> success(T value) {
        return new Success<>(value);
    }

    static <T> Result<T> failure(String errorCode, String message, ErrorType errorType) {
        return new Failure<>(errorCode, message, errorType);
    }
}