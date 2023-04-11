package com.todo.todo.service;

import com.todo.todo.exception.NotFoundException;
import com.todo.todo.pojo.Project;
import com.todo.todo.pojo.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TodoService {
    List<String> projects = new ArrayList<>();
    Map<String, List<String>> ptmap = new HashMap<>();

    private final Logger LOGGER = LoggerFactory.getLogger(TodoService.class);
    public void addProject(Project project) {
        LOGGER.info("TodoService :: addProject() :: start");
        projects.add(project.getProjectName());
        LOGGER.info("TodoService :: addProject() :: end");
    }

    public List<String> getProjects() {
        LOGGER.info("TodoService :: getProjects() :: start");
        LOGGER.info("TodoService :: getProjects() :: end");
        return projects;
    }

    public void deleteProject(String projectName) throws NotFoundException{
        LOGGER.info("TodoService :: deleteProject() :: start");
        if(!projects.contains(projectName)) {
            LOGGER.error("Project not found to delete.");
            throw new NotFoundException(HttpStatus.NOT_FOUND.value(), "Project "+projectName+" not found to delete.");
        }
        ptmap.remove(projectName);
        projects.remove(projectName);
        LOGGER.info("TodoService :: deleteProject() :: end");
    }

    public void addTask(Task task) {
        LOGGER.info("TodoService :: addTask() :: start");
        String projectName = task.getProjectName();
        List<String> tasks = ptmap.get(projectName);

        if(!projects.contains(task.getProjectName())) {
            LOGGER.error("Projects not found.");
            throw new NotFoundException(HttpStatus.NOT_FOUND.value(), "Project not found to add tasks.");
        }
        if(null == tasks && !ptmap.containsKey(projectName)) {
            ptmap.put(task.getProjectName(), task.getTasks());
            return;
        }
        task.getTasks().stream().forEach(taskName -> {
            if(!tasks.contains(taskName)) {
                tasks.add(taskName);
            }
        });
        ptmap.put(projectName, tasks);
        LOGGER.info("TodoService :: addTask() :: end");
        return;
    }

    public List<String> getTasks(String projectName) {
        LOGGER.info("TodoService :: getTasks() :: start");
        if(projects.contains(projectName) && ptmap.containsKey(projectName)) {
            List<String> projectTasks = ptmap.get(projectName);
            LOGGER.info("TodoService :: getTasks() :: end");
            return projectTasks;
        }
        LOGGER.error("Project not found to get tasks.");
        throw new NotFoundException(HttpStatus.NOT_FOUND.value(), "Tasks for project"+projectName+"not found.");
    }

    public void deleteTasks(Task task) {
        LOGGER.info("TodoService :: deleteTasks() :: start");
        String projectName = task.getProjectName();
        List<String> projectTasks = ptmap.get(projectName);
        if(null != projectTasks && null != task.getTasks()) {
            task.getTasks().stream().forEach(taskName -> {
                if(projectTasks.contains(taskName)) {
                    projectTasks.remove(taskName);
                } else {
                    LOGGER.error("Task "+taskName+" not present.");
                    throw new NotFoundException(HttpStatus.NOT_FOUND.value(), "Task "+taskName+" not found to delete.");
                }
            });
            ptmap.put(projectName, projectTasks);
            return;
        }
        LOGGER.info("TodoService :: deleteTasks() :: end");
        return;
    }
}
