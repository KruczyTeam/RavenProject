package com.kruczyteam.raven.Task.service;

import com.kruczyteam.raven.Backlog.model.Backlog;
import com.kruczyteam.raven.ProgressState;
import com.kruczyteam.raven.Task.exception.TaskInvalidUserStoryException;
import com.kruczyteam.raven.Task.exception.TaskNotFoundException;
import com.kruczyteam.raven.Task.model.Task;
import com.kruczyteam.raven.Task.repository.ITaskRepository;
import com.kruczyteam.raven.UserStory.model.UserStory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TaskService implements ITaskService
{
    private ITaskRepository iTaskRepository;

    @Autowired
    public TaskService(ITaskRepository iTaskRepository)
    {
        this.iTaskRepository = iTaskRepository;
    }

    @Override
    public List<Task> getTasks(UserStory userStory)
    {
        return iTaskRepository.findByUserStory(userStory);
    }

    @Override
    public void addTask(UserStory userStory, Task task)
    {
        task.setUserStory(userStory);
        task.setCreationDate(LocalDate.now());
        task.setProgressState(ProgressState.TODO);

        iTaskRepository.save(task);
    }

    @Override
    public Task getTask(UserStory userStory, Long taskId)
    {
        return validateTask(userStory, taskId);
    }

    @Override
    public void updateTask(UserStory userStory, Long taskId, Task task)
    {
        Task tempTask = validateTask(userStory, taskId);

        // todo przemyśleć, które dane chciałbym aktualizować
        task.setId(taskId);
        task.setUserStory(userStory);
        task.setProgressState(tempTask.getProgressState());
        task.setCreationDate(tempTask.getCreationDate());

        iTaskRepository.save(task);
    }

    @Override
    public void deleteTask(UserStory userStory, Long taskId)
    {
        validateTask(userStory, taskId);

        iTaskRepository.delete(taskId);
    }

    @Override
    public void setTaskProgressState(UserStory userStory, Long taskId, ProgressState progressState)
    {
        Task tempTask = validateTask(userStory, taskId);

        tempTask.setProgressState(progressState);
        iTaskRepository.save(tempTask);
    }

    @Override
    public Task validateTask(UserStory userStory, Long taskId)
    {
        Task tempTask = iTaskRepository.findOne(taskId);

        if(tempTask != null)
        {
            if(tempTask.getUserStory().getId().equals(userStory.getId()))
            {
                return tempTask;
            }
            else
            {
                throw new TaskInvalidUserStoryException(taskId, userStory.getId());
            }
        }
        else
        {
            throw new TaskNotFoundException(taskId);
        }
    }
}
