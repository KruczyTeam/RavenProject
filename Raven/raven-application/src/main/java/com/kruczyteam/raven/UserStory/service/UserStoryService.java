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

@Service
public class UserStoryService implements IUserStoryService
{
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
        return validateUserStory(backlog, userStoryId);
    }

    @Override
    public void updateUserStory(Backlog backlog, Long userStoryId, UserStory userStory)
    {
        UserStory tempUserStory = validateUserStory(backlog, userStoryId);

        userStory.setId(userStoryId);
        userStory.setBacklog(backlog);
        userStory.setProgressState(tempUserStory.getProgressState());

        iUserStoryRepository.save(userStory);
    }

    @Override
    public void deleteUserStory(Backlog backlog, Long userStoryId)
    {
        validateUserStory(backlog, userStoryId);

        iUserStoryRepository.delete(userStoryId);
    }

    @Override
    public void setUserStoryProgressState(Backlog backlog, Long userStoryId, ProgressState progressState)
    {
        UserStory tempUserStory = validateUserStory(backlog, userStoryId);

        tempUserStory.setProgressState(progressState);
        iUserStoryRepository.save(tempUserStory);
    }

    @Override
    public UserStory validateUserStory(Backlog backlog, Long userStoryId)
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
}
