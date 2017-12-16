package com.kruczyteam.raven.Task.exception;

public class TaskInvalidUserStoryException extends RuntimeException
{
	public TaskInvalidUserStoryException(Long taskId, Long userStoryId)
	{
		super("UserInformation Story with id: " + userStoryId + ", doesnt have task with id: " + taskId);
	}
}
