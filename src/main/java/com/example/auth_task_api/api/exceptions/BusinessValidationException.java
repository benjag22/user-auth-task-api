package com.example.auth_task_api.api.exceptions;

import com.example.auth_task_api.api.dto.FieldErrorItem;

import java.util.List;

public class BusinessValidationException extends RuntimeException {

    private final List<FieldErrorItem> errors;

    public BusinessValidationException(String message, List<FieldErrorItem> errors) {
        super(message);
        this.errors = errors;
    }

    public List<FieldErrorItem> getErrors() {
        return errors;
    }
}