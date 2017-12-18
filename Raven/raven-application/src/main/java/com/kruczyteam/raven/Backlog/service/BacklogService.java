package com.kruczyteam.raven.Backlog.service;

import com.kruczyteam.raven.Backlog.exception.BacklogNotFoundException;
import com.kruczyteam.raven.Backlog.model.Backlog;
import com.kruczyteam.raven.Backlog.repository.IBacklogRepository;
import com.kruczyteam.raven.History.Enums.Operation;
import com.kruczyteam.raven.History.Enums.Services;
import com.kruczyteam.raven.History.service.IHistoryService;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BacklogService implements IBacklogService
{
	private IBacklogRepository backlogRepository;
	private IHistoryService historyService;

	@Autowired
	public BacklogService(IBacklogRepository backlogRepository, IHistoryService historyService)
	{
		this.backlogRepository = backlogRepository;
		this.historyService = historyService;
	}

	public List<Backlog> getBacklogs()
	{
		ArrayList<Backlog> backlogs = Lists.newArrayList(backlogRepository.findAll());
		String ids = "";
		for (Backlog backlog : backlogs)
		{
			ids += backlog.getId() + ";";
		}
		historyService.AddToHistory(Services.BackLog, Operation.Get, ids);

		return backlogs;
	}

	public void addBacklog(Backlog backlog)
	{
		Backlog newBacklog = backlogRepository.save(backlog);
		historyService.AddToHistory(Services.BackLog, Operation.Add, newBacklog.getId().toString());
	}

	public Backlog getBacklog(Long backlogId)
	{
		Backlog backlog = validateBacklog(backlogId);
		historyService.AddToHistory(Services.BackLog, Operation.Get, backlogId.toString());
		return backlog;
	}

	public void updateBacklog(Long backlogId, Backlog backlog)
	{
		validateBacklog(backlogId);
		backlog.setId(backlogId);

		backlogRepository.save(backlog);
		historyService.AddToHistory(Services.BackLog, Operation.Update, backlogId.toString());
	}

	public void deleteBacklog(Long backlogId)
	{
		validateBacklog(backlogId);

		backlogRepository.delete(backlogId);
		historyService.AddToHistory(Services.BackLog, Operation.Delete, backlogId.toString());
	}

	@Override
	public Backlog validateBacklog(Long backlogId)
	{
		Backlog tempBacklog = backlogRepository.findOne(backlogId);

		if (tempBacklog != null)
		{
			return tempBacklog;
		} else
		{
			throw new BacklogNotFoundException(backlogId);
		}
	}
}
