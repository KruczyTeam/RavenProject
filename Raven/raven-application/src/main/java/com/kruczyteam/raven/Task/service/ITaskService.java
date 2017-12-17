package com.kruczyteam.raven.Task.service;

import com.kruczyteam.raven.Backlog.model.Backlog;
import com.kruczyteam.raven.ProgressState;
import com.kruczyteam.raven.Task.model.Task;
import com.kruczyteam.raven.UserStory.model.UserStory;

import java.util.List;

public interface ITaskService
{
    List<Task> getTasks(UserStory userStory);
    void addTask(UserStory userStory, Task task);
    Task getTask(UserStory userStory, Long taskId);
    void updateTask(UserStory userStory, Long taskId, Task task);
    void deleteTask(UserStory userStory, Long taskId);

    void setTaskProgressState(UserStory userStory, Long taskId, ProgressState progressState);

    Task validateTask(UserStory userStory, Long taskId);
}
