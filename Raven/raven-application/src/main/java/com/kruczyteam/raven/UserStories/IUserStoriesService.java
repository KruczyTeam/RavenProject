package com.kruczyteam.raven.UserStories;

import com.kruczyteam.raven.BackLog.Backlog;

import java.util.List;

public interface IUserStoriesService {

    List<UserStories> getAllUserStories(Backlog backlog);
    void addUserStories(Backlog backlog, UserStories userStories);
}
