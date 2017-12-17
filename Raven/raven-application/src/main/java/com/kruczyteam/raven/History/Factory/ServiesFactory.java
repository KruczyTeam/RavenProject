package com.kruczyteam.raven.History.Factory;

import com.kruczyteam.raven.Backlog.service.BacklogService;
import com.kruczyteam.raven.History.Enums.Services;
import org.springframework.beans.factory.annotation.Autowired;

public class ServiesFactory
{
	private BacklogService backlogService;

	@Autowired
	public ServiesFactory(BacklogService backlogService)
	{
		this.backlogService = backlogService;
	}

	public void GetServiceByName(String name)
	{
		Services service = Services.valueOf(name);
		switch (service)
		{
			case BackLog:
			{
				//return backlogService;
			}
			default:
			{
				//return null;
			}
		}
	}
}
