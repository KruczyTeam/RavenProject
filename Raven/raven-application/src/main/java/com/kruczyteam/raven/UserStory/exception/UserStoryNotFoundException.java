package com.kruczyteam.raven.UserStory.exception;

public class UserStoryNotFoundException extends RuntimeException
{
	public UserStoryNotFoundException(Long id)
	{
		super("Cannot find user story with id: " + id);
	}
}
