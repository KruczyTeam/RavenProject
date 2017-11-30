package com.kruczyteam.raven.UserStories;

import com.kruczyteam.raven.BackLog.BackLogService;
import com.kruczyteam.raven.BackLog.Backlog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
public class UserStoriesController {

    @Autowired
    UserStoriesService userStoriesService;

    @Autowired
    BackLogService backLogService;

    @RequestMapping(value = "/backlogs/{id}/UserStories/", method = RequestMethod.GET)
    public List<UserStories> getUserStories(@PathVariable Long id)
    {
        Backlog backlog = backLogService.getBacklog(id);
        return userStoriesService.getAllUserStories(backlog);
    }

    @RequestMapping(value = "/backlogs/{id}/UserStories/", method = RequestMethod.POST)
    public void addtUserStories(@PathVariable Long id, @RequestBody UserStories userStories)
    {
        Backlog backlog = backLogService.getBacklog(id);
        userStoriesService.addUserStories(backlog,userStories);
    }
}
