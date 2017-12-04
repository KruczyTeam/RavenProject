package com.kruczyteam.raven.UserStory.controller;

import com.kruczyteam.raven.User.exception.UserNotFoundException;
import com.kruczyteam.raven.UserStory.exception.UserStoryNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class UserStoryControllerExceptionHandler
{
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler({UserStoryNotFoundException.class, UserNotFoundException.class})
	public void handleConflict()
    {

	}
}
