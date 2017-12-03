package com.kruczyteam.raven.UserStories.controller;

import com.kruczyteam.raven.UserStories.exception.UserStoriesNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class UserStoriesControllerExceptionHandler
{
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(UserStoriesNotFoundException.class)
	public void handleConflict()
    {

	}
}
