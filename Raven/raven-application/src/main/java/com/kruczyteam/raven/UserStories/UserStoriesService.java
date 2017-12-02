package com.kruczyteam.raven.UserStories;

import com.kruczyteam.raven.BackLog.Backlog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserStoriesService implements IUserStoriesService {

    @Autowired
    private IUserStoriesRepository userStoriesRepository;

    @Override
    public List<UserStories> getAllUserStories(Backlog backlog){
        List<UserStories> userStories = userStoriesRepository.findByfBacklog(backlog);

        for(UserStories userStory: userStories)
        {
            userStory.setfBacklog(null);
        }

       return userStories;
    }

    @Override
    public void addUserStories(Backlog backlog, UserStories userStories) {
        userStories.setfBacklog(backlog);
        userStoriesRepository.save(userStories);
    }

    @Override
    public UserStories getUserStories(Backlog backlog, Long idUserStory) throws UserStoriesNotFoundException {
        try {
            UserStories story = userStoriesRepository.findByfBacklogAndUserStoriesId(backlog, idUserStory);
            story.setfBacklog(null);
            return story;
        }
        catch(Exception e)
        {
            throw new UserStoriesNotFoundException();
        }
    }

    @Override
    public void updateUserStories(Backlog backlog, Long idUserStory, UserStories userStories) throws UserStoriesNotFoundException {
        try {
            UserStories story = userStoriesRepository.findByfBacklogAndUserStoriesId(backlog, idUserStory);
            if(story!=null) {
                userStories.setUserStoriesId(idUserStory);
                userStories.setfBacklog(backlog);
                userStoriesRepository.save(userStories);
            }
            else
            {
                throw new Exception();
            }
        }
        catch(Exception e)
        {
            throw new UserStoriesNotFoundException();
        }
    }

    @Override
    public void deleteUserStories(Backlog backlog, Long idUserStory) throws UserStoriesNotFoundException {

        try {
            UserStories story = userStoriesRepository.findByfBacklogAndUserStoriesId(backlog, idUserStory);
            userStoriesRepository.delete(story);
        }
        catch(Exception e)
        {
            throw new UserStoriesNotFoundException();
        }
    }

}
