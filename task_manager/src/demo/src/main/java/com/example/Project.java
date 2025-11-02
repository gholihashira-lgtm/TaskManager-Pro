package com.example;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Project {
    private String id;
    private String name;
    private String description;
    private LocalDateTime createdAt;
    private List<Task> tasks;

    public Project(String name, String description) {
        this.id = "PROJ_" + System.currentTimeMillis() + "_" + (int)(Math.random() * 1000);
        this.name = name;
        this.description = description;
        this.createdAt = LocalDateTime.now();
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(Task task) {
        tasks.remove(task);
    }

    public int getCompletedTaskCount() {
        return (int) tasks.stream()
                .filter(task -> task.getStatus() == TaskStatus.COMPLETED)
                .count();
    }

    public double getCompletionPercentage() {
        if (tasks.isEmpty()) return 0.0;
        return (getCompletedTaskCount() * 100.0) / tasks.size();
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public List<Task> getTasks() { return new ArrayList<>(tasks); }
    public int getTaskCount() { return tasks.size(); }
}