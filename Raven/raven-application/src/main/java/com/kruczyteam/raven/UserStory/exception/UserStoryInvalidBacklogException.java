package com.kruczyteam.raven.UserStory.exception;

public class UserStoryInvalidBacklogException extends RuntimeException
{
    public UserStoryInvalidBacklogException(Long userStoryId, Long backlogId)
    {
        super("User story with id: " + userStoryId + ", doesnt have backlog with id: " + backlogId);
    }
}
