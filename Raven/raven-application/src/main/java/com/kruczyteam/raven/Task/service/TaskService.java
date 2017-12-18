package com.kruczyteam.raven.Task.service;

import com.kruczyteam.raven.History.Enums.Operation;
import com.kruczyteam.raven.History.Enums.Services;
import com.kruczyteam.raven.History.service.IHistoryService;
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
	private IHistoryService historyService;

	@Autowired
	public TaskService(ITaskRepository iTaskRepository, IHistoryService historyService)
	{
		this.iTaskRepository = iTaskRepository;
		this.historyService = historyService;
	}

	@Override
	public List<Task> getTasks(UserStory userStory)
	{
		List<Task> tasks = iTaskRepository.findByUserStory(userStory);
		String ids = "";
		for (Task task : tasks)
		{
			ids += task.getId() + ";";
		}
		historyService.AddToHistory(Services.Task, Operation.Get, ids);

		return tasks;
	}

	@Override
	public void addTask(UserStory userStory, Task task)
	{
		task.setUserStory(userStory);
		task.setCreationDate(LocalDate.now());
		task.setProgressState(ProgressState.TODO);

		Task newTask = iTaskRepository.save(task);
		historyService.AddToHistory(Services.Task, Operation.Add, newTask.getId().toString());
	}

	@Override
	public Task getTask(UserStory userStory, Long taskId)
	{
		Task task = validateTask(userStory, taskId);
		historyService.AddToHistory(Services.Task, Operation.Get, taskId.toString());

		return task;
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
		historyService.AddToHistory(Services.Task, Operation.Update, taskId.toString());
	}

	@Override
	public void deleteTask(UserStory userStory, Long taskId)
	{
		validateTask(userStory, taskId);

		iTaskRepository.delete(taskId);
		historyService.AddToHistory(Services.Task, Operation.Delete, taskId.toString());
	}

	@Override
	public void setTaskProgressState(UserStory userStory, Long taskId, ProgressState progressState)
	{
		Task tempTask = validateTask(userStory, taskId);

		tempTask.setProgressState(progressState);
		iTaskRepository.save(tempTask);

		historyService.AddToHistory(Services.Task, Operation.Update, taskId.toString());
	}

	@Override
	public Task validateTask(UserStory userStory, Long taskId)
	{
		Task tempTask = iTaskRepository.findOne(taskId);

		if (tempTask != null)
		{
			if (tempTask.getUserStory().getId().equals(userStory.getId()))
			{
				return tempTask;
			} else
			{
				throw new TaskInvalidUserStoryException(taskId, userStory.getId());
			}
		} else
		{
			throw new TaskNotFoundException(taskId);
		}
	}
}
