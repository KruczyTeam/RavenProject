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
        try
        {
            if(iBacklogRepository.findOne(backlogId) != null)
            {
                return iBacklogRepository.findOne(backlogId);
            }
            else
            {
                throw new Exception();
            }
        }
        catch(Exception e)
        {
            throw new BacklogNotFoundException(backlogId);
        }
    }

    public void updateBacklog(Long backlogId, Backlog backlog)
    {
        try
        {
            if(getBacklog(backlogId) != null)
            {
                iBacklogRepository.save(backlog);
            }
            else
            {
                throw new Exception();
            }
        }
        catch (Exception e)
        {
            throw new BacklogNotFoundException(backlogId);
        }
    }

    public void deleteBacklog(Long backlogId)
    {
        try
        {
            if(getBacklog(backlogId) != null)
            {
                iBacklogRepository.delete(backlogId);
            }
            else
            {
                throw new Exception();
            }
        }
        catch(Exception e)
        {
            throw new BacklogNotFoundException(backlogId);
        }
    }
}
