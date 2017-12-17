package com.kruczyteam.raven.UserStory.service;

import com.kruczyteam.raven.Backlog.model.Backlog;
import com.kruczyteam.raven.UserStory.model.UserStory;

import java.util.List;

public interface IUserStoryService
{
    List<UserStory> getUserStories(Backlog backlog);
    void addUserStory(Backlog backlog, UserStory userStory);
    UserStory getUserStory(Backlog backlog, Long userStoryId);
    void updateUserStory(Backlog backlog, Long userStoryId, UserStory userStory);
    void deleteUserStory(Backlog backlog, Long userStoryId);

    UserStory validateUserStory(Backlog backlog, Long userStoryId);
}
