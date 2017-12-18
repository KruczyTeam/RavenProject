package com.kruczyteam.raven.History.service;

import com.kruczyteam.raven.History.Enums.Operation;
import com.kruczyteam.raven.History.Enums.Services;

public interface IHistoryService
{
	void AddToHistory(Services serviceName, Operation operation, String oldData);
}
