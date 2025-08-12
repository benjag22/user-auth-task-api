package com.example.auth_task_api.api.exceptions;

import java.util.List;

public record ApiErrorResponse(
        String message,
        int status,
        List<FieldErrorItem> fieldErrors
) {}
