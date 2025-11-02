package com.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.stream.Collectors;

public class TaskManager {
    private Map<String, Project> projects;
    private Map<String, Task> tasks;
    private Scanner scanner;

    public TaskManager() {
        this.projects = new HashMap<>();
        this.tasks = new HashMap<>();
        this.scanner = new Scanner(System.in);
        initializeSampleData();
    }

    private void initializeSampleData() {
        Project universityProject = new Project("University Assignments", "All university tasks and assignments");
        Project personalProject = new Project("Personal Projects", "Personal coding projects");
        
        projects.put(universityProject.getId(), universityProject);
        projects.put(personalProject.getId(), personalProject);

        Task task1 = new SimpleTask("Complete Math Homework", 
            "Solve calculus problems (Ch. 5-8)", TaskPriority.HIGH,
            LocalDateTime.now().plusDays(2), universityProject.getId());
        
        Task task2 = new SimpleTask("Prepare Java Presentation", 
            "Create slides for OOP presentation", TaskPriority.URGENT,
            LocalDateTime.now().plusDays(1), universityProject.getId());

        tasks.put(task1.getId(), task1);
        tasks.put(task2.getId(), task2);
        
        universityProject.addTask(task1);
        universityProject.addTask(task2);
    }

    public void start() {
        while (true) {
            displayMainMenu();
            int choice = getIntInput("Enter your choice: ");
            
            switch (choice) {
                case 1 -> manageProjects();
                case 2 -> manageTasks();
                case 3 -> viewDashboard();
                case 4 -> searchAndFilterTasks();
                case 0 -> {
                    System.out.println("👋 Thank you for using Task Manager Pro!");
                    return;
                }
                default -> System.out.println("❌ Invalid choice. Please try again.");
            }
        }
    }

    private void displayMainMenu() {
        System.out.println();
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║              TASK MANAGER PRO                ║");
        System.out.println("╚══════════════════════════════════════════════╝");
        System.out.println(" 1. 📁 Manage Projects                       ");
        System.out.println(" 2. ✅ Manage Tasks                           ");
        System.out.println(" 3. 📊 View Dashboard                         ");
        System.out.println(" 4. 🔍 Search & Filter Tasks                  ");
        System.out.println(" 0. 🚪 Exit                                   ");
        System.out.println();
    }

    private void manageProjects() {
        while (true) {
            System.out.println();
            System.out.println("╔══════════════════════════════════════════════╗");
            System.out.println("║               PROJECT MANAGER                ║");
            System.out.println("╚══════════════════════════════════════════════╝");
            System.out.println(" 1. ➕ Create New Project                     ");
            System.out.println(" 2. 👁️ View All Projects                     ");
            System.out.println(" 3. 📝 Edit Project                          ");
            System.out.println(" 4. 🗑️ Delete Project                         ");
            System.out.println(" 5. 📋 View Project Details                  ");
            System.out.println(" 0. ↩️ Back to Main Menu                      ");
            System.out.println();

            int choice = getIntInput("Enter your choice: ");
            
            switch (choice) {
                case 1 -> createProject();
                case 2 -> viewAllProjects();
                case 3 -> editProject();
                case 4 -> deleteProject();
                case 5 -> viewProjectDetails();
                case 0 -> { return; }
                default -> System.out.println("❌ Invalid choice. Please try again.");
            }
        }
    }

    private void createProject() {
        System.out.println();
        System.out.println("🏗️ Creating a New Project");
        System.out.println("─────────────────────────");
        
        String name = getStringInput("Enter project name: ");
        String description = getStringInput("Enter project description: ");
        
        Project project = new Project(name, description);
        projects.put(project.getId(), project);
        
        System.out.println("✅ Project '" + name + "' created successfully!");
    }

    private void viewAllProjects() {
        System.out.println();
        System.out.println("┌───────────────────────────────────────────────┐");
        System.out.println("│                 YOUR PROJECTS                 │");
        System.out.println("└───────────────────────────────────────────────┘");
        
        if (projects.isEmpty()) {
            System.out.println("📭 No projects found. Create your first project!");
            return;
        }
        
        for (Project project : projects.values()) {
            System.out.println(" 🎯 " + project.getName());
            System.out.println("   📝 Description: " + project.getDescription());
            System.out.printf("   ✅ Tasks: %d | 📊 Completion: %.0f%%\n", 
                project.getTaskCount(), project.getCompletionPercentage());
            System.out.println();
        }
    }

    private void editProject() {
        System.out.println("📝 Edit Project feature coming soon!");
    }

    private void deleteProject() {
        System.out.println("🗑️ Delete Project feature coming soon!");
    }

    private void viewProjectDetails() {
        System.out.println("📋 View Project Details feature coming soon!");
    }

    private void manageTasks() {
        while (true) {
            System.out.println();
            System.out.println("╔══════════════════════════════════════════════╗");
            System.out.println("║                TASK MANAGER                  ║");
            System.out.println("╚══════════════════════════════════════════════╝");
            System.out.println(" 1. ➕ Add New Task                            ");
            System.out.println(" 2. 👁️ View All Tasks                         ");
            System.out.println(" 3. ✏️ Edit Task                              ");
            System.out.println(" 4. ✅ Mark Task as Complete                  ");
            System.out.println(" 5. 🗑️ Delete Task                            ");
            System.out.println(" 6. 📅 View Tasks by Priority                 ");
            System.out.println(" 0. ↩️ Back to Main Menu                       ");
            System.out.println();

            int choice = getIntInput("Enter your choice: ");
            
            switch (choice) {
                case 1 -> addNewTask();
                case 2 -> viewAllTasks();
                case 3 -> editTask();
                case 4 -> markTaskComplete();
                case 5 -> deleteTask();
                case 6 -> viewTasksByPriority();
                case 0 -> { return; }
                default -> System.out.println("❌ Invalid choice. Please try again.");
            }
        }
    }

    private void addNewTask() {
        System.out.println();
        System.out.println("✨ Adding a New Task");
        System.out.println("────────────────────");
        
        Project project = selectProject();
        if (project == null) return;
        
        String title = getStringInput("Enter task title: ");
        String description = getStringInput("Enter task description: ");
        
        System.out.println("Select task type:");
        System.out.println("1. Simple Task");
        System.out.println("2. Recurring Task");
        System.out.println("3. Project Task");
        int typeChoice = getIntInput("Choice: ");
        
        System.out.println("Select priority:");
        for (TaskPriority priority : TaskPriority.values()) {
            System.out.println((priority.ordinal() + 1) + ". " + priority.getDisplayName());
        }
        int priorityChoice = getIntInput("Choice: ");
        TaskPriority priority = TaskPriority.values()[priorityChoice - 1];
        
        LocalDateTime dueDate = getDateInput("Enter due date (YYYY-MM-DD): ");
        
        Task task;
        switch (typeChoice) {
            case 1 -> task = new SimpleTask(title, description, priority, dueDate, project.getId());
            case 2 -> {
                String pattern = getStringInput("Enter recurrence pattern (DAILY/WEEKLY/MONTHLY): ");
                int interval = getIntInput("Enter interval: ");
                task = new RecurringTask(title, description, priority, dueDate, project.getId(), pattern, interval);
            }
            case 3 -> task = new ProjectTask(title, description, priority, dueDate, project.getId());
            default -> {
                System.out.println("❌ Invalid task type. Creating Simple Task.");
                task = new SimpleTask(title, description, priority, dueDate, project.getId());
            }
        }
        
        tasks.put(task.getId(), task);
        project.addTask(task);
        
        System.out.println("✅ Task '" + title + "' added successfully!");
    }

    private void viewAllTasks() {
        System.out.println();
        System.out.println("┌───────────────────────────────────────────────┐");
        System.out.println("│                  ALL TASKS                    │");
        System.out.println("└───────────────────────────────────────────────┘");
        
        if (tasks.isEmpty()) {
            System.out.println("📭 No tasks found. Create your first task!");
            return;
        }
        
        for (Task task : tasks.values()) {
            displayTask(task);
        }
    }

    private void displayTask(Task task) {
        Project project = projects.get(task.getProjectId());
        String projectName = project != null ? project.getName() : "No Project";
        
        System.out.println(" 📋 " + task.getTitle());
        System.out.println("   📝 " + task.getDescription());
        System.out.println("   🏷️ Type: " + task.getType().getDisplayName());
        System.out.println("   ⚡ Priority: " + task.getPriority().getDisplayName() + 
                         "      " + task.getStatus().getDisplayName());
        System.out.println("   📅 Due Date: " + task.getDueDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        System.out.println("   📁 Project: " + projectName);
        System.out.println();
    }

    private void editTask() {
        System.out.println("✏️ Edit Task feature coming soon!");
    }

    private void markTaskComplete() {
        List<Task> pendingTasks = tasks.values().stream()
                .filter(task -> task.getStatus() != TaskStatus.COMPLETED)
                .collect(Collectors.toList());
        
        if (pendingTasks.isEmpty()) {
            System.out.println("🎉 All tasks are already completed!");
            return;
        }
        
        System.out.println("Select task to mark complete:");
        for (int i = 0; i < pendingTasks.size(); i++) {
            Task task = pendingTasks.get(i);
            System.out.printf("%d. %s [%s]\n", i + 1, task.getTitle(), task.getStatus().getDisplayName());
        }
        
        int choice = getIntInput("Choice: ") - 1;
        if (choice >= 0 && choice < pendingTasks.size()) {
            Task task = pendingTasks.get(choice);
            task.setStatus(TaskStatus.COMPLETED);
            System.out.println("🎉 Task '" + task.getTitle() + "' marked as COMPLETED!");
        } else {
            System.out.println("❌ Invalid choice.");
        }
    }

    private void deleteTask() {
        System.out.println("🗑️ Delete Task feature coming soon!");
    }

    private void viewTasksByPriority() {
        System.out.println();
        for (TaskPriority priority : TaskPriority.values()) {
            List<Task> priorityTasks = tasks.values().stream()
                    .filter(task -> task.getPriority() == priority && task.getStatus() != TaskStatus.COMPLETED)
                    .collect(Collectors.toList());
            
            if (!priorityTasks.isEmpty()) {
                System.out.println("╔══════════════════════════════════════════════╗");
                System.out.printf("║              %s TASKS             ║\n", priority.name());
                System.out.println("╚══════════════════════════════════════════════╝");
                
                for (int i = 0; i < priorityTasks.size(); i++) {
                    Task task = priorityTasks.get(i);
                    Project project = projects.get(task.getProjectId());
                    String projectName = project != null ? project.getName() : "No Project";
                    
                    System.out.printf(" %d. %s %s\n", i + 1, 
                            task.getStatus() == TaskStatus.IN_PROGRESS ? "🟡" : "🔴",
                            task.getTitle());
                    System.out.printf("    📅 Due: %s | 🎯 %s\n",
                            task.getDueDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                            projectName);
                    System.out.println();
                }
            }
        }
    }

    private void viewDashboard() {
        System.out.println();
        System.out.println("┌───────────────────────────────────────────────┐");
        System.out.println("│                 DASHBOARD                     │");
        System.out.println("└───────────────────────────────────────────────┘");
        
        long totalTasks = tasks.size();
        long completedTasks = tasks.values().stream()
                .filter(task -> task.getStatus() == TaskStatus.COMPLETED)
                .count();
        double completionRate = totalTasks > 0 ? (completedTasks * 100.0) / totalTasks : 0;
        
        System.out.printf(" 📊 Total Projects: %d\n", projects.size());
        System.out.printf(" 📋 Total Tasks: %d\n", totalTasks);
        System.out.printf(" ✅ Completed: %d (%.0f%%)\n", completedTasks, completionRate);
        System.out.printf(" ⏳ Pending: %d\n", totalTasks - completedTasks);
        
        System.out.println(" ⚡ Priority Breakdown:");
        for (TaskPriority priority : TaskPriority.values()) {
            long count = tasks.values().stream()
                    .filter(task -> task.getPriority() == priority)
                    .count();
            System.out.printf("   • %s: %d tasks\n", priority.getDisplayName(), count);
        }
        
        System.out.println(" 📅 Upcoming Deadlines:");
        tasks.values().stream()
                .filter(task -> task.getStatus() != TaskStatus.COMPLETED)
                .sorted(Comparator.comparing(Task::getDueDate))
                .limit(3)
                .forEach(task -> {
                    long daysUntilDue = java.time.temporal.ChronoUnit.DAYS.between(
                            LocalDateTime.now().toLocalDate(), task.getDueDate().toLocalDate());
                    String timeframe = daysUntilDue == 0 ? "Today" : 
                                     daysUntilDue == 1 ? "Tomorrow" : 
                                     "In " + daysUntilDue + " days";
                    System.out.printf("   • %s: %s\n", timeframe, task.getTitle());
                });
    }

    private void searchAndFilterTasks() {
        System.out.println();
        System.out.println("🔍 Search & Filter Options");
        System.out.println("──────────────────────────");
        System.out.println("1. Filter by Priority");
        System.out.println("2. Filter by Status");
        System.out.println("3. Search by Title");
        System.out.println("0. Back");
        
        int choice = getIntInput("Enter your choice: ");
        
        switch (choice) {
            case 1 -> filterByPriority();
            case 2 -> filterByStatus();
            case 3 -> searchByTitle();
            case 0 -> { return; }
            default -> System.out.println("❌ Invalid choice.");
        }
    }

    private void filterByPriority() {
        System.out.println("Select priority to filter:");
        for (int i = 0; i < TaskPriority.values().length; i++) {
            System.out.println((i + 1) + ". " + TaskPriority.values()[i].getDisplayName());
        }
        int choice = getIntInput("Choice: ") - 1;
        
        if (choice >= 0 && choice < TaskPriority.values().length) {
            TaskPriority selectedPriority = TaskPriority.values()[choice];
            List<Task> filteredTasks = tasks.values().stream()
                    .filter(task -> task.getPriority() == selectedPriority)
                    .collect(Collectors.toList());
            
            System.out.println("\n📋 Tasks with " + selectedPriority.getDisplayName() + " priority:");
            filteredTasks.forEach(this::displayTask);
        }
    }

    private void filterByStatus() {
        System.out.println("Select status to filter:");
        for (int i = 0; i < TaskStatus.values().length; i++) {
            System.out.println((i + 1) + ". " + TaskStatus.values()[i].getDisplayName());
        }
        int choice = getIntInput("Choice: ") - 1;
        
        if (choice >= 0 && choice < TaskStatus.values().length) {
            TaskStatus selectedStatus = TaskStatus.values()[choice];
            List<Task> filteredTasks = tasks.values().stream()
                    .filter(task -> task.getStatus() == selectedStatus)
                    .collect(Collectors.toList());
            
            System.out.println("\n📋 Tasks with " + selectedStatus.getDisplayName() + " status:");
            filteredTasks.forEach(this::displayTask);
        }
    }

    private void searchByTitle() {
        String searchTerm = getStringInput("Enter search term: ").toLowerCase();
        List<Task> searchResults = tasks.values().stream()
                .filter(task -> task.getTitle().toLowerCase().contains(searchTerm) ||
                               task.getDescription().toLowerCase().contains(searchTerm))
                .collect(Collectors.toList());
        
        System.out.println("\n🔍 Search Results for '" + searchTerm + "':");
        searchResults.forEach(this::displayTask);
    }

    private Project selectProject() {
        if (projects.isEmpty()) {
            System.out.println("❌ No projects available. Please create a project first.");
            return null;
        }
        
        System.out.println("Select project:");
        List<Project> projectList = new ArrayList<>(projects.values());
        for (int i = 0; i < projectList.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, projectList.get(i).getName());
        }
        
        int choice = getIntInput("Choice: ") - 1;
        if (choice >= 0 && choice < projectList.size()) {
            return projectList.get(choice);
        } else {
            System.out.println("❌ Invalid choice.");
            return null;
        }
    }

    private int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("❌ Please enter a valid number.");
            }
        }
    }

    private String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private LocalDateTime getDateInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String dateStr = scanner.nextLine();
                return LocalDateTime.parse(dateStr + "T18:00");
            } catch (DateTimeParseException e) {
                System.out.println("❌ Invalid date format. Please use YYYY-MM-DD.");
            }
        }
    }
}