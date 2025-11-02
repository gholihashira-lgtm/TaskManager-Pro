package com.example;

public enum TaskPriority {
    LOW(" LOW"),
    MEDIUM(" MEDIUM"),
    HIGH(" HIGH"),
    URGENT(" URGENT");

    private final String displayName;

    TaskPriority(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}