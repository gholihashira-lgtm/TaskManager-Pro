package com.example;

import java.time.LocalDateTime;

public class SimpleTask extends Task {
    public SimpleTask(String title, String description, TaskPriority priority, 
                     LocalDateTime dueDate, String projectId) {
        super(title, description, priority, dueDate, projectId);
    }

    @Override
    public TaskType getType() {
        return TaskType.SIMPLE;
    }

    @Override
    public String getDetails() {
        return "One-time task";
    }
}