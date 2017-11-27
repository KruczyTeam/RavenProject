package com.kruczyteam.raven.BackLog;

import java.util.List;

public interface IBackLogService
{
    List<BackLog> getAllBackLogs();
    void addBackLog(BackLog backLog);
}
