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
@RequestMapping(value = "/api/v1")
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

    @RequestMapping(value = "/backlogs/{id}/userstory/", method = RequestMethod.GET)
    public List<UserStory> getUserStories(@PathVariable Long id)
    {
        return userStoryService.getUserStoriesByBacklogId(id);
    }

    @RequestMapping(value = "/backlogs/{id}/userstory/", method = RequestMethod.POST)
    public void addUserStory(@PathVariable Long id, @Valid @RequestBody UserStory userStory)
    {
        Backlog backlog = backlogService.getBacklog(id);
        userStoryService.addUserStory(userStory, backlog);
    }

    // Moze tak bedzie lepiej?

    @RequestMapping(value = "/userstory/{id}", method = RequestMethod.GET)
    public UserStory getUserStory(@PathVariable Long id) throws UserStoryNotFoundException
    {
        return userStoryService.getUserStory(id);
    }

    @RequestMapping(value = "/userstory/{id}", method = RequestMethod.DELETE)
    public void deleteUserStory(@PathVariable Long id) throws UserStoryNotFoundException
    {
        userStoryService.deleteUserStory(id);
    }

    @RequestMapping(value = "/userstory/{id}", method = RequestMethod.PUT)
    public void updateUserStory(@PathVariable Long id, @Valid @RequestBody UserStory userStory) throws UserStoryNotFoundException
    {
        userStoryService.updateUserStory(id, userStory);
    }

    // Trochę bez sensu to jest, bo po co mi id backloga do przegladania sobie userStory??? Id userStory beda pokolei i tak
    // Przyklad:
    // Backlog 1 - ma userstory1, userstory2, userstory7
    // /backlogs/1/userstory/1/
    // /backlogs/1/userstory/2/
    // /backlogs/1/userstory/7/
    // tak bedziesz musial w nie wchodzic : >

//    @RequestMapping(value = "/backlogs/{id}/userstory/{userStoryId}", method = RequestMethod.GET)
//    public UserStory getUserStory(@PathVariable Long id, @PathVariable Long userStoryId) throws UserStoryNotFoundException
//    {
//        Backlog backlog = backlogService.getBacklog(id);
//
//		return userStoryService.getUserStory(backlog, userStoryId);
//    }


//    @RequestMapping(value = "/backlogs/{id}/UserStory/{idUserStory}", method = RequestMethod.DELETE)
//    public void deleteUserStory(@PathVariable Long id,@PathVariable Long idUserStory) throws UserStoryNotFoundException {
//	    Backlog backlog = backlogService.getBacklog(id);
//	    userStoryService.deleteUserStory(backlog,idUserStory);
//    }
//
//    @RequestMapping(value = "/backlogs/{id}/UserStory/{idUserStory}", method = RequestMethod.PUT)
//    public void updateUserStory(@PathVariable Long id,@PathVariable Long idUserStory,@RequestBody UserStory userStory) throws UserStoryNotFoundException {
//	    Backlog backlog = backlogService.getBacklog(id);
//        userStoryService.updateUserStory(backlog,idUserStory,userStory);
//    }
}
