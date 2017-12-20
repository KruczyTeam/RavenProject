package com.kruczyteam.raven;

import com.kruczyteam.raven.Backlog.exception.BacklogNotFoundException;
import com.kruczyteam.raven.Task.exception.TaskNotFoundException;
import com.kruczyteam.raven.User.exception.UserExistException;
import com.kruczyteam.raven.UserStory.exception.UserStoryNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalControllerAdvice
{
	@ExceptionHandler(BacklogNotFoundException.class)
	public Exception notFoundException(final BacklogNotFoundException e)
	{
		return e;
	}

	@ExceptionHandler(UserStoryNotFoundException.class)
	public Exception notFoundException(final UserStoryNotFoundException e)
	{
		return e;
	}

	@ExceptionHandler(TaskNotFoundException.class)
	public Exception notFoundException(final TaskNotFoundException e)
	{
		return e;
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public Exception assertionException(final IllegalArgumentException e)
	{
		return e;
	}

	@ExceptionHandler(UserExistException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	public void userExistException()
	{
	}
}
