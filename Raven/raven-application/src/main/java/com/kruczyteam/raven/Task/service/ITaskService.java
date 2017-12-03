package com.kruczyteam.raven.Task.service;

import com.kruczyteam.raven.Task.model.Task;
import com.kruczyteam.raven.UserStory.model.UserStory;

import java.util.List;

public interface ITaskService
{
    List<Task> getTasksByUserStoryId(Long userStoryId);
    void addTask(Task task, UserStory userStory);

    Task getTask(Long id);
    void deleteTask(Long id);
    void updateTask(Long id, Task task);
}
