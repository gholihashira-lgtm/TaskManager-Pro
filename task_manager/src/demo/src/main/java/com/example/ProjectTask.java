package com.example;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ProjectTask extends Task {
    private List<String> subTasks;
    private double progress;

    public ProjectTask(String title, String description, TaskPriority priority,
                      LocalDateTime dueDate, String projectId) {
        super(title, description, priority, dueDate, projectId);
        this.subTasks = new ArrayList<>();
        this.progress = 0.0;
    }

    @Override
    public TaskType getType() {
        return TaskType.PROJECT;
    }

    @Override
    public String getDetails() {
        return String.format("Project with %d sub-tasks (%.1f%% complete)", 
                           subTasks.size(), progress);
    }

    public void addSubTask(String subTask) {
        subTasks.add(subTask);
        updateProgress();
    }

    public void removeSubTask(String subTask) {
        subTasks.remove(subTask);
        updateProgress();
    }

    private void updateProgress() {
        this.progress = subTasks.isEmpty() ? 0 : 30.0;
    }

    public List<String> getSubTasks() { return new ArrayList<>(subTasks); }
    public double getProgress() { return progress; }
}