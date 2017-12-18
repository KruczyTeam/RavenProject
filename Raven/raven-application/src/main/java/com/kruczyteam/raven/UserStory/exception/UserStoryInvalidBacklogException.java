package com.kruczyteam.raven.UserStory.exception;

public class UserStoryInvalidBacklogException extends RuntimeException
{
	public UserStoryInvalidBacklogException(Long userStoryId, Long backlogId)
	{
		super("Backlog with id: " + backlogId + ", doesnt have UserInformation Story with id: " + userStoryId);
	}
}
