package com.kruczyteam.raven.UserStories.service;

import com.kruczyteam.raven.Backlog.model.Backlog;
import com.kruczyteam.raven.UserStories.exception.UserStoriesNotFoundException;
import com.kruczyteam.raven.UserStories.model.UserStories;
import com.kruczyteam.raven.UserStories.repository.IUserStoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

@Service
public class UserStoriesService implements IUserStoriesService
{
    private IUserStoriesRepository userStoriesRepository;

    @Autowired
    public UserStoriesService(IUserStoriesRepository userStoriesRepository)
    {
        this.userStoriesRepository = userStoriesRepository;
    }

    @Override
    public List<UserStories> getAllUserStoriesByBacklogId(Long backlogId)
    {
       return userStoriesRepository.findByBacklogId(backlogId);
    }

    @Override
    public void addUserStories(UserStories userStories, Backlog backlog)
    {
        userStories.setBacklog(backlog);
        userStoriesRepository.save(userStories);
    }

    @Override
    public UserStories getUserStories(Long id) throws UserStoriesNotFoundException
    {
        try
        {
            return userStoriesRepository.findOne(id);
        }
        catch(Exception e)
        {
            throw new UserStoriesNotFoundException();
        }
    }

    @Override
    public void deleteUserStories(Long id) throws UserStoriesNotFoundException
    {
        try
        {
            if(userStoriesRepository.exists(id))
            {
                userStoriesRepository.delete(id);
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
    public void updateUserStories(Long id, UserStories userStories) throws UserStoriesNotFoundException
    {
        try
        {
            if(userStoriesRepository.exists(id))
            {
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

    //    @Override
//    public UserStories getUserStories(Backlog backlog, Long userStoriesId) throws UserStoriesNotFoundException
//    {
//        try
//        {
//            return userStoriesRepository.findByBacklogAndId(backlog, userStoriesId);
//        }
//        catch(Exception e)
//        {
//            throw new UserStoriesNotFoundException();
//        }
//    }

//    @Override
//    public void updateUserStories(Backlog backlog, Long idUserStory, UserStories userStories) throws UserStoriesNotFoundException
//    {
//        try {
//            UserStories story = userStoriesRepository.findByfBacklogAndUserStoriesId(backlog, idUserStory);
//            if(story!=null) {
//                userStories.setId(idUserStory);
//                userStories.setfBacklogId(backlog);
//                userStoriesRepository.save(userStories);
//            }
//            else
//            {
//                throw new Exception();
//            }
//        }
//        catch(Exception e)
//        {
//            throw new UserStoriesNotFoundException();
//        }
//    }
//
//    @Override
//    public void deleteUserStories(Backlog backlog, Long idUserStory) throws UserStoriesNotFoundException {
//
//        try {
//            UserStories story = userStoriesRepository.findByfBacklogAndUserStoriesId(backlog, idUserStory);
//            userStoriesRepository.delete(story);
//        }
//        catch(Exception e)
//        {
//            throw new UserStoriesNotFoundException();
//        }
//    }

}
