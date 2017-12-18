package com.kruczyteam.raven.Task.exception;

public class TaskNotFoundException extends RuntimeException
{
	public TaskNotFoundException(Long taskId)
	{
		super("Cannot find task with id: " + taskId);
	}
}