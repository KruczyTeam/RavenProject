package com.kruczyteam.raven.UserStory.service;

import com.kruczyteam.raven.Backlog.model.Backlog;
import com.kruczyteam.raven.ProgressState;
import com.kruczyteam.raven.UserStory.exception.UserStoryInvalidBacklogException;
import com.kruczyteam.raven.UserStory.exception.UserStoryNotFoundException;
import com.kruczyteam.raven.UserStory.model.UserStory;
import com.kruczyteam.raven.UserStory.repository.IUserStoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class UserStoryService implements IUserStoryService
{
    private final Logger logger = Logger.getLogger(getClass().getName());

    private IUserStoryRepository iUserStoryRepository;

    @Autowired
    public UserStoryService(IUserStoryRepository iUserStoryRepository)
    {
        this.iUserStoryRepository = iUserStoryRepository;
    }

    @Override
    public List<UserStory> getUserStories(Backlog backlog)
    {
        return iUserStoryRepository.findByBacklog(backlog);
    }

    @Override
    public void addUserStory(Backlog backlog, UserStory userStory)
    {
        userStory.setBacklog(backlog);
        userStory.setProgressState(ProgressState.TODO);

        iUserStoryRepository.save(userStory);
    }

    @Override
    public UserStory getUserStory(Backlog backlog, Long userStoryId)
    {
        try
        {
            UserStory tempUserStory = iUserStoryRepository.findOne(userStoryId);

            if(tempUserStory != null)
            {
                if(tempUserStory.getBacklog().getId().equals(backlog.getId()))
                {
                    return tempUserStory;
                }
                else
                {
                    throw new UserStoryInvalidBacklogException(userStoryId, backlog.getId());
                }
            }
            else
            {
                throw new UserStoryNotFoundException(userStoryId);
            }
        }
        catch (UserStoryInvalidBacklogException | UserStoryNotFoundException e)
        {
            logger.log(Level.SEVERE, e.getMessage(), e);

            throw e;
        }
    }

    @Override
    public void updateUserStory(Backlog backlog, Long userStoryId, UserStory userStory)
    {
        try
        {
            UserStory tempUserStory = iUserStoryRepository.findOne(userStoryId);

            if(tempUserStory != null)
            {
                if(tempUserStory.getBacklog().getId().equals(backlog.getId()))
                {
                    userStory.setId(userStoryId);
                    userStory.setBacklog(backlog);
                    userStory.setProgressState(tempUserStory.getProgressState());

                    iUserStoryRepository.save(userStory);
                }
                else
                {
                    throw new UserStoryInvalidBacklogException(userStoryId, backlog.getId());
                }
            }
            else
            {
                throw new UserStoryNotFoundException(userStoryId);
            }
        }
        catch (UserStoryInvalidBacklogException | UserStoryNotFoundException e)
        {
            logger.log(Level.SEVERE, e.getMessage(), e);

            throw e;
        }
    }

    @Override
    public void deleteUserStory(Backlog backlog, Long userStoryId)
    {
        try
        {
            UserStory tempUserStory = iUserStoryRepository.findOne(userStoryId);

            if(tempUserStory != null)
            {
                if(tempUserStory.getBacklog().getId().equals(backlog.getId()))
                {
                    iUserStoryRepository.delete(userStoryId);
                }
                else
                {
                    throw new UserStoryInvalidBacklogException(userStoryId, backlog.getId());
                }
            }
            else
            {
                throw new UserStoryNotFoundException(userStoryId);
            }
        }
        catch (UserStoryInvalidBacklogException | UserStoryNotFoundException e)
        {
            logger.log(Level.SEVERE, e.getMessage(), e);

            throw e;
        }
    }
}
