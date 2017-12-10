package com.kruczyteam.raven.Task.service;

import com.kruczyteam.raven.GlobalControllerAdvice;
import com.kruczyteam.raven.ProgressState;
import com.kruczyteam.raven.Task.exception.TaskInvalidUserStoryException;
import com.kruczyteam.raven.Task.exception.TaskNotFoundException;
import com.kruczyteam.raven.Task.model.Task;
import com.kruczyteam.raven.Task.repository.ITaskRepository;
import com.kruczyteam.raven.UserStory.model.UserStory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;

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
        task.setCreationDate(LocalDateTime.now());
        task.setProgressState(ProgressState.TODO);

        iTaskRepository.save(task);
    }

    @Override
    public Task getTask(UserStory userStory, Long taskId)
    {
        try
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
        catch (TaskInvalidUserStoryException | TaskNotFoundException e)
        {
            GlobalControllerAdvice.LOGGER.log(Level.SEVERE, e.getMessage(), e);

            throw e;
        }
    }

    @Override
    public void updateTask(UserStory userStory, Long taskId, Task task)
    {
        try
        {
            Task tempTask = iTaskRepository.findOne(taskId);

            if(tempTask != null)
            {
                if(tempTask.getUserStory().getId().equals(userStory.getId()))
                {
                    // todo przemyśleć, które dane chciałbym aktualizować
                    task.setId(taskId);
                    task.setUserStory(userStory);
                    task.setProgressState(tempTask.getProgressState());
                    task.setCreationDate(tempTask.getCreationDate());

                    iTaskRepository.save(task);
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
        catch (TaskInvalidUserStoryException | TaskNotFoundException e)
        {
            GlobalControllerAdvice.LOGGER.log(Level.SEVERE, e.getMessage(), e);

            throw e;
        }
    }

    @Override
    public void deleteTask(UserStory userStory, Long taskId)
    {
        try
        {
            Task tempTask = iTaskRepository.findOne(taskId);

            if(tempTask != null)
            {
                if(tempTask.getUserStory().getId().equals(userStory.getId()))
                {
                    iTaskRepository.delete(taskId);
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
        catch (TaskInvalidUserStoryException | TaskNotFoundException e)
        {
            GlobalControllerAdvice.LOGGER.log(Level.SEVERE, e.getMessage(), e);

            throw e;
        }
    }
}
