package com.kruczyteam.raven.Backlog.controller;

import com.kruczyteam.raven.Backlog.service.BacklogService;
import com.kruczyteam.raven.Backlog.model.Backlog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/backlogs")
public class BacklogController
{
    private BacklogService backlogService;

    @Autowired
    public BacklogController(BacklogService backlogService)
    {
        this.backlogService = backlogService;
    }

    @GetMapping(value = "/")
    public List<Backlog> getAllBacklogs()
    {
        return backlogService.getAllBacklogs();
    }

    @PostMapping(value = "/")
    public void addBacklog(@Valid @RequestBody Backlog backlog)
    {
        backlogService.addBacklog(backlog);
    }

    @GetMapping(value = "/{backlogId}")
    public Backlog getBacklog(@PathVariable Long backlogId)
    {
        return backlogService.getBacklog(backlogId);
    }

    @PutMapping(value = "/{backlogId}")
    public void updateBacklog(@PathVariable Long backlogId, @Valid @RequestBody Backlog backlog)
    {
        backlogService.updateBacklog(backlogId, backlog);
    }

    @DeleteMapping(value = "/{backlogId}")
    public void deleteBacklog(@PathVariable Long backlogId)
    {
        backlogService.deleteBacklog(backlogId);
    }
}
