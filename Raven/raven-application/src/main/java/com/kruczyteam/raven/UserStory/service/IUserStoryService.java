package com.kruczyteam.raven.UserStory.service;

import com.kruczyteam.raven.Backlog.model.Backlog;
import com.kruczyteam.raven.ProgressState;
import com.kruczyteam.raven.UserStory.model.UserStory;
import org.springframework.security.access.annotation.Secured;

import java.util.List;

public interface IUserStoryService
{
    @Secured({"ROLE_Admin","ROLE_User"})
    List<UserStory> getUserStories(Backlog backlog);
    @Secured({"ROLE_Admin","ROLE_User"})
    void addUserStory(Backlog backlog, UserStory userStory);
    @Secured({"ROLE_Admin","ROLE_User"})
    UserStory getUserStory(Backlog backlog, Long userStoryId);
    @Secured({"ROLE_Admin","ROLE_User"})
    void updateUserStory(Backlog backlog, Long userStoryId, UserStory userStory);
    @Secured({"ROLE_Admin"})
    void deleteUserStory(Backlog backlog, Long userStoryId);
    @Secured({"ROLE_Admin","ROLE_User"})
    void setUserStoryProgressState(Backlog backlog, Long userStoryId, ProgressState progressState);

    UserStory validateUserStory(Backlog backlog, Long userStoryId);
}
