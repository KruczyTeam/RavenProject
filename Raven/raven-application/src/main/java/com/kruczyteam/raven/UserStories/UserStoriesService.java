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
       return userStoriesRepository.findByfBacklog(backlog);
    }

    @Override
    public void addUserStories(Backlog backlog, UserStories userStories) {
        userStories.setfBacklog(backlog);
        userStoriesRepository.save(userStories);
    }
}
