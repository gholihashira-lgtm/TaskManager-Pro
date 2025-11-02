package com.example;

import java.time.LocalDateTime;

public abstract class Task {
    protected String id;
    protected String title;
    protected String description;
    protected TaskPriority priority;
    protected TaskStatus status;
    protected LocalDateTime dueDate;
    protected LocalDateTime createdAt;
    protected String projectId;

    public Task(String title, String description, TaskPriority priority, LocalDateTime dueDate, String projectId) {
        this.id = generateId();
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = TaskStatus.PENDING;
        this.dueDate = dueDate;
        this.createdAt = LocalDateTime.now();
        this.projectId = projectId;
    }

    private String generateId() {
        return "TASK_" + System.currentTimeMillis() + "_" + (int)(Math.random() * 1000);
    }

    public abstract TaskType getType();
    public abstract String getDetails();

    public String getId() { return id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public TaskPriority getPriority() { return priority; }
    public void setPriority(TaskPriority priority) { this.priority = priority; }
    public TaskStatus getStatus() { return status; }
    public void setStatus(TaskStatus status) { this.status = status; }
    public LocalDateTime getDueDate() { return dueDate; }
    public void setDueDate(LocalDateTime dueDate) { this.dueDate = dueDate; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public String getProjectId() { return projectId; }
    public void setProjectId(String projectId) { this.projectId = projectId; }

    public boolean isOverdue() {
        return LocalDateTime.now().isAfter(dueDate) && status != TaskStatus.COMPLETED;
    }
}