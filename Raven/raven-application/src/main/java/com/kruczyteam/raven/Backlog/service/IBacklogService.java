package com.kruczyteam.raven.Backlog.service;

import com.kruczyteam.raven.Backlog.exception.BacklogNotFoundException;
import com.kruczyteam.raven.Backlog.model.Backlog;

import java.util.List;

public interface IBacklogService
{
    List<Backlog> getAllBacklogs();
    void addBacklog(Backlog backlog);
    Backlog getBacklog(Long id) throws BacklogNotFoundException;
    void updateBacklog(Long id, Backlog backlog) throws BacklogNotFoundException;
    void removeBacklog(Long id) throws BacklogNotFoundException;
}
