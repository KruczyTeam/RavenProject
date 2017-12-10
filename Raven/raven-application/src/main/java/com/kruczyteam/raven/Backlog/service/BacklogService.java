package com.kruczyteam.raven.Backlog.service;

import com.kruczyteam.raven.Backlog.exception.BacklogNotFoundException;
import com.kruczyteam.raven.Backlog.model.Backlog;
import com.kruczyteam.raven.Backlog.repository.IBacklogRepository;
import com.kruczyteam.raven.GlobalControllerAdvice;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;

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
        catch (BacklogNotFoundException e)
        {
            GlobalControllerAdvice.LOGGER.log(Level.SEVERE, e.getMessage(), e);

            throw e;
        }
    }

    public void updateBacklog(Long backlogId, Backlog backlog)
    {
        try
        {
            Backlog tempBacklog = iBacklogRepository.findOne(backlogId);

            if(tempBacklog != null)
            {
                backlog.setId(backlogId);
                iBacklogRepository.save(backlog);
            }
            else
            {
                throw new BacklogNotFoundException(backlogId);
            }
        }
        catch (BacklogNotFoundException e)
        {
            GlobalControllerAdvice.LOGGER.log(Level.SEVERE, e.getMessage(), e);

            throw e;
        }
    }

    public void deleteBacklog(Long backlogId)
    {
        try
        {
            Backlog tempBacklog = iBacklogRepository.findOne(backlogId);

            if(tempBacklog != null)
            {
                iBacklogRepository.delete(backlogId);
            }
            else
            {
                throw new BacklogNotFoundException(backlogId);
            }
        }
        catch (BacklogNotFoundException e)
        {
            GlobalControllerAdvice.LOGGER.log(Level.SEVERE, e.getMessage(), e);

            throw e;
        }
    }
}
