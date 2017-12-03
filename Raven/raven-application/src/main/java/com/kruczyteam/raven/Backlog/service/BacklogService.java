package com.kruczyteam.raven.Backlog.service;

import com.kruczyteam.raven.Backlog.model.Backlog;
import com.kruczyteam.raven.Backlog.repository.IBacklogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BacklogService implements IBacklogService
{
    private IBacklogRepository backlogRepository;

    @Autowired
    public BacklogService(IBacklogRepository backlogRepository)
    {
        this.backlogRepository = backlogRepository;
    }

    public List<Backlog> getAllBacklogs()
    {
        List<Backlog> backlogs = new ArrayList();

        backlogRepository.findAll().forEach(backlogs::add);

        return backlogs;
    }

    public void addBacklog(Backlog backlog)
    {
        backlogRepository.save(backlog);
    }

    public Backlog getBacklog(long id)
    {
        return backlogRepository.findOne(id);
    }

    public void removeBacklog(long id)
    {
        backlogRepository.delete(id);
    }

    public void updateBacklog(long id, Backlog backlog)
    {
        backlog.setId(id);
        backlogRepository.save(backlog);
    }
}
