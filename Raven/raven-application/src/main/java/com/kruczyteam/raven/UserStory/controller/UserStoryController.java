package com.kruczyteam.raven.UserStory.controller;

import com.kruczyteam.raven.Backlog.service.BacklogService;
import com.kruczyteam.raven.Backlog.model.Backlog;
import com.kruczyteam.raven.UserStory.model.UserStory;
import com.kruczyteam.raven.UserStory.exception.UserStoryNotFoundException;
import com.kruczyteam.raven.UserStory.service.UserStoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/backlogs/{backlogId}/userstories")
public class UserStoryController
{
    private UserStoryService userStoryService;
    private BacklogService backlogService;

    @Autowired
    public UserStoryController(UserStoryService userStoryService, BacklogService backlogService)
    {
        this.userStoryService = userStoryService;
        this.backlogService = backlogService;
    }

    @GetMapping(value = "/")
    public List<UserStory> getUserStories(@PathVariable Long backlogId)
    {
        return userStoryService.getUserStoriesByBacklogId(backlogId);
    }

    @PostMapping(value = "/")
    public void addUserStory(@PathVariable Long backlogId, @Valid @RequestBody UserStory userStory)
    {
        userStory.setBacklog(backlogService.getBacklog(backlogId));
        userStoryService.addUserStory(userStory);
    }

    @GetMapping(value = "/{userStoryId}")
    public UserStory getUserStory(@PathVariable Long backlogId, @PathVariable Long userStoryId) throws UserStoryNotFoundException
    {
        return userStoryService.getUserStoryByBacklogIdAndUserStoryId(backlogId, userStoryId);
    }

    @PutMapping(value = "/{userStoryId}")
    public void updateUserStory(@PathVariable Long backlogId, @PathVariable Long userStoryId, @Valid @RequestBody UserStory userStory) throws UserStoryNotFoundException
    {
        Backlog backlog = backlogService.getBacklog(backlogId);

        userStoryService.updateUserStory(backlog, userStoryId, userStory);
    }

    @DeleteMapping(value = "/{userStoryId}")
    public void deleteUserStory(@PathVariable Long backlogId, @PathVariable Long userStoryId) throws UserStoryNotFoundException
    {
        userStoryService.deleteUserStory(backlogId, userStoryId);
    }
}
