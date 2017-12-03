package com.kruczyteam.raven.UserStories.controller;

import com.kruczyteam.raven.Backlog.service.BacklogService;
import com.kruczyteam.raven.Backlog.model.Backlog;
import com.kruczyteam.raven.UserStories.model.UserStories;
import com.kruczyteam.raven.UserStories.exception.UserStoriesNotFoundException;
import com.kruczyteam.raven.UserStories.service.UserStoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
public class UserStoriesController
{
    private UserStoriesService userStoriesService;

    private BacklogService backlogService;

    @Autowired
    public UserStoriesController(UserStoriesService userStoriesService, BacklogService backlogService)
    {
        this.userStoriesService = userStoriesService;
        this.backlogService = backlogService;
    }

    @RequestMapping(value = "/backlogs/{id}/userstories/", method = RequestMethod.GET)
    public List<UserStories> getAllUserStories(@PathVariable Long id)
    {
        return userStoriesService.getAllUserStoriesByBacklogId(id);
    }

    @RequestMapping(value = "/backlogs/{id}/userstories/", method = RequestMethod.POST)
    public void addUserStories(@PathVariable Long id, @Valid @RequestBody UserStories userStories)
    {
        Backlog backlog = backlogService.getBacklog(id);
        userStoriesService.addUserStories(userStories, backlog);
    }

    // Moze tak bedzie lepiej?

    @RequestMapping(value = "/userstories/{id}", method = RequestMethod.GET)
    public UserStories getUserStories(@PathVariable Long id) throws UserStoriesNotFoundException
    {
        return userStoriesService.getUserStories(id);
    }

    @RequestMapping(value = "/userstories/{id}", method = RequestMethod.DELETE)
    public void deleteUserStories(@PathVariable Long id) throws UserStoriesNotFoundException
    {
        userStoriesService.deleteUserStories(id);
    }

    @RequestMapping(value = "/userstories/{id}", method = RequestMethod.PUT)
    public void updateUserStories(@PathVariable Long id, @Valid @RequestBody UserStories userStories) throws UserStoriesNotFoundException
    {
        userStoriesService.updateUserStories(id, userStories);
    }

    // Trochę bez sensu to jest, bo po co mi id backloga do przegladania sobie userStories??? Id userStories beda pokolei i tak
    // Przyklad:
    // Backlog 1 - ma userstories1, userstories2, userstories7
    // /backlogs/1/userstories/1/
    // /backlogs/1/userstories/2/
    // /backlogs/1/userstories/7/
    // tak bedziesz musial w nie wchodzic : >

//    @RequestMapping(value = "/backlogs/{id}/userstories/{userStoriesId}", method = RequestMethod.GET)
//    public UserStories getUserStories(@PathVariable Long id, @PathVariable Long userStoriesId) throws UserStoriesNotFoundException
//    {
//        Backlog backlog = backlogService.getBacklog(id);
//
//		return userStoriesService.getUserStories(backlog, userStoriesId);
//    }


//    @RequestMapping(value = "/backlogs/{id}/UserStories/{idUserStory}", method = RequestMethod.DELETE)
//    public void deleteUserStories(@PathVariable Long id,@PathVariable Long idUserStory) throws UserStoriesNotFoundException {
//	    Backlog backlog = backlogService.getBacklog(id);
//	    userStoriesService.deleteUserStories(backlog,idUserStory);
//    }
//
//    @RequestMapping(value = "/backlogs/{id}/UserStories/{idUserStory}", method = RequestMethod.PUT)
//    public void updateUserStories(@PathVariable Long id,@PathVariable Long idUserStory,@RequestBody UserStories userStories) throws UserStoriesNotFoundException {
//	    Backlog backlog = backlogService.getBacklog(id);
//        userStoriesService.updateUserStories(backlog,idUserStory,userStories);
//    }
}
