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
    BackLogService backlogService;

    @RequestMapping(value = "/backlogs/{id}/UserStories/", method = RequestMethod.GET)
    public List<UserStories> getUserStories(@PathVariable Long id)
    {
        Backlog backlog = backlogService.getBacklog(id);
        return userStoriesService.getAllUserStories(backlog);
    }

    @RequestMapping(value = "/backlogs/{id}/UserStories/", method = RequestMethod.POST)
    public void addtUserStories(@PathVariable Long id, @RequestBody UserStories userStories)
    {
        Backlog backlog = backlogService.getBacklog(id);
        userStoriesService.addUserStories(backlog,userStories);
    }

    @RequestMapping(value = "/backlogs/{id}/UserStories/{idUserStory}", method = RequestMethod.GET)
    public UserStories getUserStory(@PathVariable Long id,@PathVariable Long idUserStory) throws UserStoriesNotFoundException {
	    Backlog backlog = backlogService.getBacklog(id);

		    return userStoriesService.getUserStories(backlog,idUserStory);

    }

    @RequestMapping(value = "/backlogs/{id}/UserStories/{idUserStory}", method = RequestMethod.DELETE)
    public void deleteUserStories(@PathVariable Long id,@PathVariable Long idUserStory) throws UserStoriesNotFoundException {
	    Backlog backlog = backlogService.getBacklog(id);
	    userStoriesService.deleteUserStories(backlog,idUserStory);
    }

    @RequestMapping(value = "/backlogs/{id}/UserStories/{idUserStory}", method = RequestMethod.PUT)
    public void updateUserStories(@PathVariable Long id,@PathVariable Long idUserStory,@RequestBody UserStories userStories) throws UserStoriesNotFoundException {
	    Backlog backlog = backlogService.getBacklog(id);
        userStoriesService.updateUserStories(backlog,idUserStory,userStories);
    }
}
