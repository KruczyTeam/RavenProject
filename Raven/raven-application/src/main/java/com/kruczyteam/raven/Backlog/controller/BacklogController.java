package com.kruczyteam.raven.Backlog.controller;

import com.kruczyteam.raven.Backlog.service.BacklogService;
import com.kruczyteam.raven.Backlog.model.Backlog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
public class BacklogController
{
    private BacklogService backlogService;

    @Autowired
    public BacklogController(BacklogService backlogService)
    {
        this.backlogService = backlogService;
    }

    @RequestMapping(value = "/backlogs/")
    public List<Backlog> getAllBacklogs()
    {
        return backlogService.getAllBacklogs();
    }

    @RequestMapping(value = "/backlogs/", method = RequestMethod.POST)
    public void addBacklog(@Valid @RequestBody Backlog backlog)
    {
        backlogService.addBacklog(backlog);
    }

    @RequestMapping(value = "/backlogs/{id}", method = RequestMethod.GET)
    public Backlog getBacklog(@PathVariable Long id)
    {
        return backlogService.getBacklog(id);
    }

    @RequestMapping(value = "/backlogs/{id}", method = RequestMethod.DELETE)
    public void deleteBacklog(@PathVariable Long id)
    {
        backlogService.removeBacklog(id);
    }

    @RequestMapping(value = "/backlogs/{id}", method = RequestMethod.PUT)
    public void updateBacklog(@PathVariable Long id, @Valid @RequestBody Backlog backlog)
    {
        backlogService.updateBacklog(id,backlog);
    }



}
