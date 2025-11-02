package com.example;

public enum TaskStatus {
    PENDING(" PENDING"),
    IN_PROGRESS(" IN_PROGRESS"),
    COMPLETED(" COMPLETED");

    private final String displayName;

    TaskStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}