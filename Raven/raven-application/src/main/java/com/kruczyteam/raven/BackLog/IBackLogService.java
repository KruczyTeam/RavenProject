package com.kruczyteam.raven.BackLog;

import java.util.List;

public interface IBackLogService
{
    List<Backlog> getAllBacklogs();
    void addBacklog(Backlog backlog);
    Backlog getBacklog(long id);
    void removeBacklog(long id);
    void updateBacklog(long id, Backlog backlog);
}
