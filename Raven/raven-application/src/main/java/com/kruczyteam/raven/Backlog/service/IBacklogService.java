package com.kruczyteam.raven.Backlog.service;

import com.kruczyteam.raven.Backlog.exception.BacklogNotFoundException;
import com.kruczyteam.raven.Backlog.model.Backlog;
import org.springframework.security.access.annotation.Secured;

import java.util.List;

public interface IBacklogService
{
    @Secured({"ROLE_Admin","ROLE_User"})
    List<Backlog> getBacklogs();
	@Secured({"ROLE_Admin","ROLE_User"})
	void addBacklog(Backlog backlog);
	@Secured({"ROLE_Admin"})
	Backlog getBacklog(Long backlogId) throws BacklogNotFoundException;
	@Secured({"ROLE_Admin"})
	void updateBacklog(Long backlogId, Backlog backlog) throws BacklogNotFoundException;
	@Secured({"ROLE_Admin"})
	void deleteBacklog(Long backlogId) throws BacklogNotFoundException;
}
