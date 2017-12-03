package com.kruczyteam.raven.UserStory.service;

import com.kruczyteam.raven.Backlog.model.Backlog;
import com.kruczyteam.raven.UserStory.model.UserStory;
import com.kruczyteam.raven.UserStory.exception.UserStoryNotFoundException;

import java.util.List;

public interface IUserStoryService
{
    List<UserStory> getUserStoriesByBacklogId(Long backlogId);
    void addUserStory(UserStory userStory, Backlog backlog);

    UserStory getUserStory(Long id) throws UserStoryNotFoundException;
    void deleteUserStory(Long id) throws UserStoryNotFoundException;
    void updateUserStory(Long id, UserStory userStory) throws UserStoryNotFoundException;

    // UserStory getUserStory(Backlog backlog, Long userStoryId) throws UserStoryNotFoundException;
    // void updateUserStory(Backlog backlog, Long id, UserStory userStory) throws UserStoryNotFoundException;
    // void deleteUserStory(Backlog backlog, Long id) throws UserStoryNotFoundException;
}
