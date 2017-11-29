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
    public List<Backlog> getAllBacklogs()
    {
        List<Backlog> backlogs = new ArrayList();

        backLogRepository.findAll().forEach(backlogs::add);

        return backlogs;
    }

    @Override
    public void addBacklog(Backlog backlog)
    {
        backLogRepository.save(backlog);
    }

    @Override
    public Backlog getBacklog(long id) {
        return backLogRepository.findOne(id);
    }

    @Override
    public void removeBacklog(long id) {
        backLogRepository.delete(id);
    }

    @Override
    public void updateBacklog(long id, Backlog backlog) {
        backlog.setId(id);
        backLogRepository.save(backlog);
    }
}
