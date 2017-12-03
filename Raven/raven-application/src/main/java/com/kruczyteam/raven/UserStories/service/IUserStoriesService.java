package com.kruczyteam.raven.UserStories.service;

import com.kruczyteam.raven.Backlog.model.Backlog;
import com.kruczyteam.raven.UserStories.model.UserStories;
import com.kruczyteam.raven.UserStories.exception.UserStoriesNotFoundException;

import java.util.List;

public interface IUserStoriesService
{
    List<UserStories> getAllUserStoriesByBacklogId(Long backlogId);
    void addUserStories(UserStories userStories, Backlog backlog);

    UserStories getUserStories(Long id) throws UserStoriesNotFoundException;
    void deleteUserStories(Long id) throws UserStoriesNotFoundException;
    void updateUserStories(Long id, UserStories userStories) throws UserStoriesNotFoundException;

    // UserStories getUserStories(Backlog backlog, Long userStoriesId) throws UserStoriesNotFoundException;
    // void updateUserStories(Backlog backlog, Long id, UserStories userStories) throws UserStoriesNotFoundException;
    // void deleteUserStories(Backlog backlog, Long id) throws UserStoriesNotFoundException;
}
