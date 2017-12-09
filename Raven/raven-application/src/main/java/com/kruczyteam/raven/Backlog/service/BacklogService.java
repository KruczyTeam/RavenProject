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

    public List<Backlog> getAllBacklogs()
    {
        return Lists.newArrayList(iBacklogRepository.findAll());
    }

    public void addBacklog(Backlog backlog)
    {
        iBacklogRepository.save(backlog);
    }

    public Backlog getBacklog(Long id)
    {
        try
        {
            if(iBacklogRepository.findOne(id) != null)
            {
                return iBacklogRepository.findOne(id);
            }
            else
            {
                throw new Exception();
            }
        }
        catch(Exception e)
        {
            throw new BacklogNotFoundException(id);
        }
    }

    public void updateBacklog(Long id, Backlog backlog)
    {
        try
        {
            if(getBacklog(id) != null)
            {
                backlog.setId(id);
                iBacklogRepository.save(backlog);
            }
            else
            {
                throw new Exception();
            }
        }
        catch (Exception e)
        {
            throw new BacklogNotFoundException(id);
        }
    }

    public void removeBacklog(Long id)
    {
        try
        {
            if(getBacklog(id) != null)
            {
                iBacklogRepository.delete(id);
            }
            else
            {
                throw new Exception();
            }
        }
        catch(Exception e)
        {
            throw new BacklogNotFoundException(id);
        }
    }
}
