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
    private IUserStoryRepository iUserStoryRepository;

    @Autowired
    public UserStoryService(IUserStoryRepository iUserStoryRepository)
    {
        this.iUserStoryRepository = iUserStoryRepository;
    }

    @Override
    public List<UserStory> getUserStoriesByBacklogId(Long backlogId)
    {
       return iUserStoryRepository.findByBacklogId(backlogId);
    }

    @Override
    public UserStory getUserStoryByBacklogIdAndUserStoryId(Long backlogId, Long userStoryId) throws UserStoryNotFoundException
    {
        try
        {
            return iUserStoryRepository.findOneByBacklogIdAndId(backlogId, userStoryId);
        }
        catch(Exception e)
        {
            throw new UserStoryNotFoundException();
        }
    }

    @Override
    public void addUserStory(UserStory userStory)
    {
        iUserStoryRepository.save(userStory);
    }

    @Override
    public void updateUserStory(Backlog backlog, Long userStoryId, UserStory userStory) throws UserStoryNotFoundException
    {
        try
        {
            if(getUserStoryByBacklogIdAndUserStoryId(backlog.getId(), userStoryId) != null)
            {
                userStory.setBacklog(backlog);
                userStory.setId(userStoryId);

                iUserStoryRepository.save(userStory);
            }
            else
            {
                throw new Exception();
            }
        }
        catch (Exception e)
        {
            throw new UserStoryNotFoundException();
        }
    }

    @Override
    public void deleteUserStory(Long backlogId, Long userStoryId) throws UserStoryNotFoundException
    {
        try
        {
            UserStory userStory = getUserStoryByBacklogIdAndUserStoryId(backlogId, userStoryId);

            iUserStoryRepository.delete(userStory);
        }
        catch(Exception e)
        {
            throw new UserStoryNotFoundException();
        }
    }
}
