package com.kruczyteam.raven.Task.service;

import com.kruczyteam.raven.ProgressState;
import com.kruczyteam.raven.Task.model.Task;
import com.kruczyteam.raven.UserStory.model.UserStory;
import org.springframework.security.access.annotation.Secured;

import java.util.List;

public interface ITaskService
{
	@Secured({"ROLE_Admin", "ROLE_User"})
	List<Task> getTasks(UserStory userStory);

	@Secured({"ROLE_Admin", "ROLE_User"})
	void addTask(UserStory userStory, Task task);

	@Secured({"ROLE_Admin", "ROLE_User"})
	Task getTask(UserStory userStory, Long taskId);

	@Secured({"ROLE_Admin", "ROLE_User"})
	void updateTask(UserStory userStory, Long taskId, Task task);

	@Secured({"ROLE_Admin"})
	void deleteTask(UserStory userStory, Long taskId);

	@Secured({"ROLE_Admin", "ROLE_User"})
	void setTaskProgressState(UserStory userStory, Long taskId, ProgressState progressState);

	Task validateTask(UserStory userStory, Long taskId);
}
