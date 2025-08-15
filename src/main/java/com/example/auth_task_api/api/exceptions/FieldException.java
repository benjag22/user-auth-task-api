package com.example.auth_task_api.api.exceptions;

import lombok.Getter;

@Getter
public class FieldException extends RuntimeException {

    private final String field;

    public FieldException(String field, String message) {
        super(message);
        this.field = field;
    }

}