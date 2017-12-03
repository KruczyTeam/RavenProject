package com.kruczyteam.raven.Task.controller;

import com.kruczyteam.raven.Task.model.Task;
import com.kruczyteam.raven.Task.service.TaskService;
import com.kruczyteam.raven.UserStory.exception.UserStoryNotFoundException;
import com.kruczyteam.raven.UserStory.model.UserStory;
import com.kruczyteam.raven.UserStory.service.UserStoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
public class TaskController
{
    private TaskService taskService;
    private UserStoryService userStoryService;

    @Autowired
    public TaskController(TaskService taskService, UserStoryService userStoryService)
    {
        this.taskService = taskService;
        this.userStoryService = userStoryService;
    }

    @GetMapping(value = "/userstories/{id}/tasks/")
    public List<Task> getTasks(@PathVariable Long id)
    {
        return taskService.getTasksByUserStoryId(id);
    }

    // fix exception
    @PostMapping(value = "/userstories/{id}/tasks/")
    public void addTask(@PathVariable Long id, @Valid @RequestBody Task task) throws UserStoryNotFoundException
    {
        UserStory userStory = userStoryService.getUserStory(id);
        taskService.addTask(task, userStory);
    }

    @GetMapping(value = "/tasks/{id}")
    public Task getTask(@PathVariable Long id)
    {
        return taskService.getTask(id);
    }

    @DeleteMapping(value = "/tasks/{id}")
    public void deleteTask(@PathVariable Long id)
    {
        taskService.deleteTask(id);
    }

    @PutMapping(value = "/tasks/{id}")
    public void updateTask(@PathVariable Long id, @Valid @RequestBody Task task)
    {
        taskService.updateTask(id, task);
    }
}
