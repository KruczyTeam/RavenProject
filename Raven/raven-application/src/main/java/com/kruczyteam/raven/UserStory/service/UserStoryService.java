package com.kruczyteam.raven.UserStory.service;

import com.kruczyteam.raven.Backlog.model.Backlog;
import com.kruczyteam.raven.History.Enums.Operation;
import com.kruczyteam.raven.History.Enums.Services;
import com.kruczyteam.raven.History.service.IHistoryService;
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
	private IHistoryService historyService;

	@Autowired
	public UserStoryService(IUserStoryRepository iUserStoryRepository, IHistoryService historyService)
	{
		this.iUserStoryRepository = iUserStoryRepository;
		this.historyService = historyService;
	}

	@Override
	public List<UserStory> getUserStories(Backlog backlog)
	{
		List<UserStory> userStories = iUserStoryRepository.findByBacklog(backlog);
		String ids = "";
		for (UserStory userStory : userStories)
		{
			ids += userStory.getId() + ";";
		}
		historyService.AddToHistory(Services.UserStory, Operation.Get, ids);

		return userStories;
	}

	@Override
	public void addUserStory(Backlog backlog, UserStory userStory)
	{
		userStory.setBacklog(backlog);
		userStory.setProgressState(ProgressState.TODO);

		UserStory story = iUserStoryRepository.save(userStory);
		historyService.AddToHistory(Services.UserStory, Operation.Add, story.getId().toString());
	}

	@Override
	public UserStory getUserStory(Backlog backlog, Long userStoryId)
	{
		UserStory userStory = validateUserStory(backlog, userStoryId);
		historyService.AddToHistory(Services.UserStory, Operation.Get, userStoryId.toString());

		return userStory;
	}

	@Override
	public void updateUserStory(Backlog backlog, Long userStoryId, UserStory userStory)
	{
		UserStory tempUserStory = validateUserStory(backlog, userStoryId);

		userStory.setId(userStoryId);
		userStory.setBacklog(backlog);
		userStory.setProgressState(tempUserStory.getProgressState());

		iUserStoryRepository.save(userStory);
		historyService.AddToHistory(Services.UserStory, Operation.Update, userStoryId.toString());

	}

	@Override
	public void deleteUserStory(Backlog backlog, Long userStoryId)
	{
		validateUserStory(backlog, userStoryId);

		iUserStoryRepository.delete(userStoryId);
		historyService.AddToHistory(Services.UserStory, Operation.Delete, userStoryId.toString());
	}

	@Override
	public void setUserStoryProgressState(Backlog backlog, Long userStoryId, ProgressState progressState)
	{
		UserStory tempUserStory = validateUserStory(backlog, userStoryId);

		tempUserStory.setProgressState(progressState);
		iUserStoryRepository.save(tempUserStory);
		historyService.AddToHistory(Services.UserStory, Operation.Update, userStoryId.toString());
	}

	@Override
	public UserStory validateUserStory(Backlog backlog, Long userStoryId)
	{
		UserStory tempUserStory = iUserStoryRepository.findOne(userStoryId);

		if (tempUserStory != null)
		{
			if (tempUserStory.getBacklog().getId().equals(backlog.getId()))
			{
				return tempUserStory;
			} else
			{
				throw new UserStoryInvalidBacklogException(userStoryId, backlog.getId());
			}
		} else
		{
			throw new UserStoryNotFoundException(userStoryId);
		}
	}
}
