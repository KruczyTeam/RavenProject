package com.kruczyteam.raven.History.service;

import com.kruczyteam.raven.History.Enums.Operation;
import com.kruczyteam.raven.History.Enums.Services;
import com.kruczyteam.raven.History.model.History;
import com.kruczyteam.raven.History.repository.IHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistoryService implements IHistoryService
{
	IHistoryRepository historyRepository;

	@Autowired
	public HistoryService(IHistoryRepository historyRepository)
	{
		this.historyRepository = historyRepository;
	}

	public void AddToHistory(Services serviceName, Operation operation, String oldData)
	{
		History history = new History(serviceName.name(), operation.name(), oldData);
		historyRepository.save(history);
	}
}
