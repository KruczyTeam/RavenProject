package com.kruczyteam.raven.Backlog.service;

import com.kruczyteam.raven.Backlog.exception.BacklogNotFoundException;
import com.kruczyteam.raven.Backlog.model.Backlog;
import com.kruczyteam.raven.Backlog.repository.IBacklogRepository;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<Backlog> getBacklogs()
    {
        return Lists.newArrayList(iBacklogRepository.findAll());
    }

    public void addBacklog(Backlog backlog)
    {
         iBacklogRepository.save(backlog);
    }

    public Backlog getBacklog(Long backlogId)
    {
            return validateBacklog(backlogId);
    }

    public void updateBacklog(Long backlogId, Backlog backlog)
    {
            validateBacklog(backlogId);
            backlog.setId(backlogId);

            iBacklogRepository.save(backlog);
    }

    public void deleteBacklog(Long backlogId)
    {
            validateBacklog(backlogId);

            iBacklogRepository.delete(backlogId);
    }

    @Override
    public Backlog validateBacklog(Long backlogId)
    {
        Backlog tempBacklog = iBacklogRepository.findOne(backlogId);

        if(tempBacklog != null)
        {
            return tempBacklog;
        }
        else
        {
            throw new BacklogNotFoundException(backlogId);
        }
    }
}
