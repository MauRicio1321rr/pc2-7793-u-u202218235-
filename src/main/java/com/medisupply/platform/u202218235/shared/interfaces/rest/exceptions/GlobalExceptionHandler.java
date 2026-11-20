package com.medisupply.platform.u202218235.shared.interfaces.rest.exceptions;


import com.medisupply.platform.u202218235.procurement.domain.exceptions.DuplicateRequestCodeException;
import com.medisupply.platform.u202218235.procurement.domain.exceptions.ValidationException;
import com.medisupply.platform.u202218235.shared.interfaces.rest.resources.MessageResource;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private final MessageSource messageSource;

    public GlobalExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<MessageResource> handleValidationException(ValidationException ex) {
        var localizedMessage = messageSource.getMessage(
                null,
                LocaleContextHolder.getLocale()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new MessageResource(localizedMessage));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<MessageResource> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new MessageResource(ex.getMessage()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<MessageResource> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        var localizedMessage = messageSource.getMessage(
                "error.request.body.malformed",
                null,
                "Malformed or invalid JSON request body",
                LocaleContextHolder.getLocale()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new MessageResource(localizedMessage));
    }

    @ExceptionHandler(DuplicateRequestCodeException.class)
    public ResponseEntity<MessageResource> handleDuplicateRequestCodeException(DuplicateRequestCodeException ex) {
        var localizedMessage = messageSource.getMessage(
                ex.getErrorCode(),
                null,
                ex.getMessage(),
                LocaleContextHolder.getLocale()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new MessageResource(localizedMessage));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<MessageResource> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        var localizedMessage = messageSource.getMessage(
                "error.request.code.duplicate",
                null,
                "A supply request with the specified request code already exists",
                LocaleContextHolder.getLocale()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new MessageResource(localizedMessage));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MessageResource> handleGeneralException(Exception ex) {
        var localizedMessage = messageSource.getMessage(
                "error.internal.unexpected",
                null,
                "An unexpected internal error occurred",
                LocaleContextHolder.getLocale()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new MessageResource(localizedMessage));
    }
}