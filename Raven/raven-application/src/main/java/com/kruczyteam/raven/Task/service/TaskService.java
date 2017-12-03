package com.kruczyteam.raven.Task.service;

import com.kruczyteam.raven.Task.ProgressState;
import com.kruczyteam.raven.Task.model.Task;
import com.kruczyteam.raven.Task.repository.ITaskRepository;
import com.kruczyteam.raven.UserStory.model.UserStory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
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
    public List<Task> getTasksByUserStoryId(Long userStoryId)
    {
        return iTaskRepository.findByUserStoryId(userStoryId);
    }

    @Override
    public void addTask(Task task, UserStory userStory)
    {
        task.setUserStory(userStory);
        task.setCreationDate(Date.from(Instant.now()));
        task.setProgressState(ProgressState.TODO);

        iTaskRepository.save(task);
    }

    @Override
    public Task getTask(Long id)
    {
        return iTaskRepository.findOne(id);
    }

    @Override
    public void deleteTask(Long id)
    {
        if(iTaskRepository.exists(id))
        {
            iTaskRepository.delete(id);
        }
        else
        {
            //throw new Exception();
        }
    }

    @Override
    public void updateTask(Long id, Task task)
    {
        if(iTaskRepository.exists(id))
        {
            iTaskRepository.save(task);
        }
        else
        {
            //throw new Exception();
        }
    }
}
