package com.example.auth_task_api.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessValidationException.class)
    public ResponseEntity<ApiErrorResponse> handleBusinessValidation(BusinessValidationException e) {
        ApiErrorResponse body = new ApiErrorResponse(
                e.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                e.getErrors()
        );
        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ApiErrorResponse> handleDuplicate(DuplicateResourceException ex) {
        ApiErrorResponse body = new ApiErrorResponse(
                ex.getMessage(),
                HttpStatus.CONFLICT.value(),
                List.of(new FieldErrorItem(ex.getField(), ex.getMessage(), "ALREADY_EXISTS"))
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
    }
}