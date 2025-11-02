package com.example;

import java.time.LocalDateTime;

public class RecurringTask extends Task {
    private String recurrencePattern;
    private int interval;

    public RecurringTask(String title, String description, TaskPriority priority,
                        LocalDateTime dueDate, String projectId, 
                        String recurrencePattern, int interval) {
        super(title, description, priority, dueDate, projectId);
        this.recurrencePattern = recurrencePattern;
        this.interval = interval;
    }

    @Override
    public TaskType getType() {
        return TaskType.RECURRING;
    }

    @Override
    public String getDetails() {
        return String.format("Recurs every %d %s", interval, recurrencePattern.toLowerCase());
    }

    public String getRecurrencePattern() { return recurrencePattern; }
    public int getInterval() { return interval; }
}