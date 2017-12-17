package com.kruczyteam.raven.Backlog.service;

import com.kruczyteam.raven.Backlog.model.Backlog;
import org.springframework.security.access.annotation.Secured;

import java.util.List;

public interface IBacklogService
{
	@Secured({"ROLE_Admin","ROLE_User"})
    List<Backlog> getBacklogs();
	@Secured({"ROLE_Admin","ROLE_User"})
    void addBacklog(Backlog backlog);
	@Secured({"ROLE_Admin","ROLE_User"})
    Backlog getBacklog(Long backlogId);
	@Secured({"ROLE_Admin","ROLE_User"})
    void updateBacklog(Long backlogId, Backlog backlog);
	@Secured({"ROLE_Admin"})
    void deleteBacklog(Long backlogId);

    Backlog validateBacklog(Long backlogId);
}
