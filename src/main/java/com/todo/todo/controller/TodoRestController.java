package com.todo.todo.controller;

import com.todo.todo.exception.NotFoundException;
import com.todo.todo.pojo.Project;
import com.todo.todo.pojo.Task;
import com.todo.todo.service.TodoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TodoRestController.class);
    @Autowired
    TodoService todoService;

    @RequestMapping(method = RequestMethod.POST, path = "/createproject")
    public void createProject(@RequestBody Project project) {
        LOGGER.info("TodoRestController :: createProject() :: start");
        todoService.addProject(project);
        LOGGER.info("TodoRestController :: createProject() :: end");
    }

    @RequestMapping(method = RequestMethod.GET, path = "/getProjects")
    public ResponseEntity<List<String>> getProjects() throws NotFoundException {
        LOGGER.info("TodoRestController :: getProjects() :: start");
        List<String> projects = todoService.getProjects();
        LOGGER.info("TodoRestController :: getProjects() :: end");
        return ResponseEntity.ok(projects);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/deleteProject/{projectName}")
    public void deleteProject(@PathVariable String projectName) {
        LOGGER.info("TodoRestController :: deleteProject() :: start");
        todoService.deleteProject(projectName);
        LOGGER.info("TodoRestController :: deleteProject() :: end");
    }

    @RequestMapping(method = RequestMethod.POST, path = "/createTasks")
    public void createTasks(@RequestBody Task task) {
        LOGGER.info("TodoRestController :: createTasks() :: start");
        todoService.addTask(task);
        LOGGER.info("TodoRestController :: createTasks() :: end");
    }

    @RequestMapping(method = RequestMethod.GET, path = "/getTasks/{projectName}")
    public ResponseEntity<List<String>> getProjectTasks(@PathVariable String projectName) {
        LOGGER.info("TodoRestController :: getProjectTasks() :: start");
        List<String> projectTasks = todoService.getTasks(projectName);
        LOGGER.info("TodoRestController :: getProjectTasks() :: end");
        return ResponseEntity.ok(projectTasks);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/deleteTasks")
    public void deleteTasks(@RequestBody Task task) {
        LOGGER.info("TodoRestController :: deleteTasks() :: start");
        todoService.deleteTasks(task);
        LOGGER.info("TodoRestController :: deleteTasks() :: end");
    }
}
