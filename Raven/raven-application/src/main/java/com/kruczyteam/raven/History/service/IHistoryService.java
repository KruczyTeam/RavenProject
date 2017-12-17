package com.kruczyteam.raven.History.service;

import com.kruczyteam.raven.History.Enums.Services;

public interface IHistoryService
{
	void AddToHistory(Services serviceName, String operation, String oldData);
}
