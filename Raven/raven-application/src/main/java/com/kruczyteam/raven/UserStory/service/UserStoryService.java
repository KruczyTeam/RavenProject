package com.kruczyteam.raven.UserStory.service;

import com.kruczyteam.raven.Backlog.model.Backlog;
import com.kruczyteam.raven.UserStory.exception.UserStoryNotFoundException;
import com.kruczyteam.raven.UserStory.model.UserStory;
import com.kruczyteam.raven.UserStory.repository.IUserStoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserStoryService implements IUserStoryService
{
    private IUserStoryRepository userStoryRepository;

    @Autowired
    public UserStoryService(IUserStoryRepository userStoryRepository)
    {
        this.userStoryRepository = userStoryRepository;
    }

    @Override
    public List<UserStory> getUserStoriesByBacklogId(Long backlogId)
    {
       return userStoryRepository.findByBacklogId(backlogId);
    }

    @Override
    public void addUserStory(UserStory userStory, Backlog backlog)
    {
        userStory.setBacklog(backlog);
        userStoryRepository.save(userStory);
    }

    @Override
    public UserStory getUserStory(Long id) throws UserStoryNotFoundException
    {
        try
        {
            return userStoryRepository.findOne(id);
        }
        catch(Exception e)
        {
            throw new UserStoryNotFoundException();
        }
    }

    @Override
    public void deleteUserStory(Long id) throws UserStoryNotFoundException
    {
        try
        {
            if(userStoryRepository.exists(id))
            {
                userStoryRepository.delete(id);
            }
            else
            {
                throw new Exception();
            }
        }
        catch(Exception e)
        {
            throw new UserStoryNotFoundException();
        }
    }

    @Override
    public void updateUserStory(Long id, UserStory userStory) throws UserStoryNotFoundException
    {
        try
        {
            if(userStoryRepository.exists(id))
            {
                userStoryRepository.save(userStory);
            }
            else
            {
                throw new Exception();
            }
        }
        catch(Exception e)
        {
            throw new UserStoryNotFoundException();
        }
    }

    //    @Override
//    public UserStory getUserStory(Backlog backlog, Long userStoryId) throws UserStoryNotFoundException
//    {
//        try
//        {
//            return userStoryRepository.findByBacklogAndId(backlog, userStoryId);
//        }
//        catch(Exception e)
//        {
//            throw new UserStoryNotFoundException();
//        }
//    }

//    @Override
//    public void updateUserStory(Backlog backlog, Long idUserStory, UserStory userStory) throws UserStoryNotFoundException
//    {
//        try {
//            UserStory story = userStoryRepository.findByfBacklogAndUserStoryId(backlog, idUserStory);
//            if(story!=null) {
//                userStory.setId(idUserStory);
//                userStory.setfBacklogId(backlog);
//                userStoryRepository.save(userStory);
//            }
//            else
//            {
//                throw new Exception();
//            }
//        }
//        catch(Exception e)
//        {
//            throw new UserStoryNotFoundException();
//        }
//    }
//
//    @Override
//    public void deleteUserStory(Backlog backlog, Long idUserStory) throws UserStoryNotFoundException {
//
//        try {
//            UserStory story = userStoryRepository.findByfBacklogAndUserStoryId(backlog, idUserStory);
//            userStoryRepository.delete(story);
//        }
//        catch(Exception e)
//        {
//            throw new UserStoryNotFoundException();
//        }
//    }

}
