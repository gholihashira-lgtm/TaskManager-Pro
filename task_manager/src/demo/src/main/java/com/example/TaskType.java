package com.example;

public enum TaskType {
    SIMPLE("Simple Task"),
    RECURRING(" Recurring Task"),
    PROJECT(" Project Task");

    private final String displayName;

    TaskType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}