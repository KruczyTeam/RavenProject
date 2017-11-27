package com.kruczyteam.raven.BackLog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BackLogService implements IBackLogService
{
    @Autowired
    private IBackLogRepository backLogRepository;

    @Override
    public List<BackLog> getAllBackLogs()
    {
        List<BackLog> backLogs = new ArrayList();

        backLogRepository.findAll().forEach(backLogs::add);

        return backLogs;
    }

    @Override
    public void addBackLog(BackLog backLog)
    {
        backLogRepository.save(backLog);
    }
}
