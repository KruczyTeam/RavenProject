package com.kruczyteam.raven.History.service;

import com.kruczyteam.raven.History.Enums.Services;
import com.kruczyteam.raven.History.model.History;
import com.kruczyteam.raven.History.repository.IHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistoryService
{
	IHistoryRepository historyRepository;

	@Autowired
	public HistoryService(IHistoryRepository historyRepository)
	{
		this.historyRepository = historyRepository;
	}

	public void add(Services serviceName, String operation, String oldData)
	{
		History history = new History(serviceName.name(),operation,oldData);
		historyRepository.save(history);
	}
}
