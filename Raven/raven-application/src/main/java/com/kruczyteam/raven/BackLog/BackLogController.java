package com.kruczyteam.raven.BackLog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
public class BackLogController
{
    @Autowired
    private BackLogService backLogService;

    @RequestMapping(value = "/backlogs/")
    public List<BackLog> getAllBackLogs()
    {
        return backLogService.getAllBackLogs();
    }

    @RequestMapping(value = "/backlogs/", method = RequestMethod.POST)
    public void addBackLog(@RequestBody BackLog backLog)
    {
        backLogService.addBackLog(backLog);
    }
}
