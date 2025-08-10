package com.example.auth_task_api.persistence.enums;

import lombok.Getter;

@Getter
public enum TaskStatus {
    PENDING("pending"),
    IN_PROGRESS("in_progress"),
    COMPLETED("completed"),
    CANCELLED("cancelled");

    private final String name;
    TaskStatus(String name) {
        this.name = name;
    }

}
