package com.kruczyteam.raven.Backlog.service;

import com.kruczyteam.raven.Backlog.model.Backlog;

import java.util.List;

public interface IBacklogService
{
    List<Backlog> getAllBacklogs();
    void addBacklog(Backlog backlog);
    Backlog getBacklog(long id);
    void removeBacklog(long id);
    void updateBacklog(long id, Backlog backlog);
}
