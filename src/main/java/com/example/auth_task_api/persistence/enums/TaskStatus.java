package com.example.auth_task_api.persistence.enums;

public enum TaskStatus {
    PENDING("pending"),
    IN_PROGRESS("in_progress"),
    COMPLETED("completed"),
    CANCELLED("cancelled");

    private String name;
    TaskStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
