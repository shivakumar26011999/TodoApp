package com.todo.todo.pojo;

public class Project {
    String projectName;
    Task tasks;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Task getTasks() {
        return tasks;
    }

    public void setTasks(Task tasks) {
        this.tasks = tasks;
    }
}
