package com.kruczyteam.raven.Backlog.service;

import com.kruczyteam.raven.Backlog.model.Backlog;

import java.util.List;

public interface IBacklogService
{
    List<Backlog> getBacklogs();
    void addBacklog(Backlog backlog);
    Backlog getBacklog(Long backlogId);
    void updateBacklog(Long backlogId, Backlog backlog);
    void deleteBacklog(Long backlogId);

    Backlog validateBacklog(Long backlogId);
}
