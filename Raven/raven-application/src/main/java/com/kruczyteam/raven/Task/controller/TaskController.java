package com.kruczyteam.raven.Task.controller;

import com.kruczyteam.raven.Backlog.model.Backlog;
import com.kruczyteam.raven.Backlog.service.BacklogService;
import com.kruczyteam.raven.ProgressState;
import com.kruczyteam.raven.Task.model.Task;
import com.kruczyteam.raven.Task.service.TaskService;
import com.kruczyteam.raven.UserStory.model.UserStory;
import com.kruczyteam.raven.UserStory.service.UserStoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/backlogs/{backlogId}/userstories/{userStoryId}/tasks")
public class TaskController
{
	private TaskService taskService;
	private UserStoryService userStoryService;
	private BacklogService backlogService;

	@Autowired
	public TaskController(TaskService taskService, UserStoryService userStoryService, BacklogService backlogService)
	{
		this.taskService = taskService;
		this.userStoryService = userStoryService;
		this.backlogService = backlogService;
	}

	@GetMapping(value = "/")
	public List<Task> getTasks(@PathVariable Long backlogId, @PathVariable Long userStoryId)
	{
		Backlog backlog = backlogService.getBacklog(backlogId);
		UserStory userStory = userStoryService.getUserStory(backlog, userStoryId);

		return taskService.getTasks(userStory);
	}

	@PostMapping(value = "/")
	public void addTask(@PathVariable Long backlogId, @PathVariable Long userStoryId, @Valid @RequestBody Task task)
	{
		Backlog backlog = backlogService.getBacklog(backlogId);
		UserStory userStory = userStoryService.getUserStory(backlog, userStoryId);

		taskService.addTask(userStory, task);
	}

	@GetMapping(value = "/{taskId}")
	public Task getTask(@PathVariable Long backlogId, @PathVariable Long userStoryId, @PathVariable Long taskId)
	{
		Backlog backlog = backlogService.getBacklog(backlogId);
		UserStory userStory = userStoryService.getUserStory(backlog, userStoryId);

		return taskService.getTask(userStory, taskId);
	}

	@PutMapping(value = "/{taskId}")
	public void updateTask(@PathVariable Long backlogId, @PathVariable Long userStoryId, @PathVariable Long taskId, @Valid @RequestBody Task task)
	{
		Backlog backlog = backlogService.getBacklog(backlogId);
		UserStory userStory = userStoryService.getUserStory(backlog, userStoryId);

		taskService.updateTask(userStory, taskId, task);
	}

	@DeleteMapping(value = "/{taskId}")
	public void deleteTask(@PathVariable Long backlogId, @PathVariable Long userStoryId, @PathVariable Long taskId)
	{
		Backlog backlog = backlogService.getBacklog(backlogId);
		UserStory userStory = userStoryService.getUserStory(backlog, userStoryId);

		taskService.deleteTask(userStory, taskId);
	}

	@PatchMapping(value = "/{taskId}")
	public void setProgressState(@PathVariable Long backlogId, @PathVariable Long userStoryId, @PathVariable Long taskId, @Valid @RequestParam ProgressState progressState)
	{
		Backlog backlog = backlogService.getBacklog(backlogId);
		UserStory userStory = userStoryService.getUserStory(backlog, userStoryId);

		taskService.setTaskProgressState(userStory, taskId, progressState);
	}
}
