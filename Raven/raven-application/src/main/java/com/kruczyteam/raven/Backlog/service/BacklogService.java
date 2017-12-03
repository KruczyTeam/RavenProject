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
    private IBacklogRepository iBacklogRepository;

    @Autowired
    public BacklogService(IBacklogRepository iBacklogRepository)
    {
        this.iBacklogRepository = iBacklogRepository;
    }

    public List<Backlog> getAllBacklogs()
    {
        List<Backlog> backlogs = new ArrayList();

        iBacklogRepository.findAll().forEach(backlogs::add);

        return backlogs;
    }

    public void addBacklog(Backlog backlog)
    {
        iBacklogRepository.save(backlog);
    }

    public Backlog getBacklog(long id)
    {
        return iBacklogRepository.findOne(id);
    }

    public void removeBacklog(long id)
    {
        iBacklogRepository.delete(id);
    }

    public void updateBacklog(long id, Backlog backlog)
    {
        backlog.setId(id);
        iBacklogRepository.save(backlog);
    }
}
