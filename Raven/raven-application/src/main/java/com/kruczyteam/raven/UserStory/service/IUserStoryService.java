package com.kruczyteam.raven.UserStory.service;

import com.kruczyteam.raven.Backlog.model.Backlog;
import com.kruczyteam.raven.UserStory.model.UserStory;
import com.kruczyteam.raven.UserStory.exception.UserStoryNotFoundException;

import java.util.List;

public interface IUserStoryService
{
    List<UserStory> getUserStoriesByBacklogId(Long backlogId);
    UserStory getUserStoryByBacklogIdAndUserStoryId(Long backlogId, Long userStoryId) throws UserStoryNotFoundException;

    void addUserStory(UserStory userStory);
    void updateUserStory(Backlog backlog, Long userStoryId, UserStory userStory) throws UserStoryNotFoundException;
    void deleteUserStory(Long backlogId, Long userStoryId) throws UserStoryNotFoundException;
}
