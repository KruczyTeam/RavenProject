package com.kruczyteam.raven.Backlog.exception;

public class BacklogNotFoundException extends RuntimeException
{
    public BacklogNotFoundException(Long id)
    {
        super("Cannot find backlog with id: " + id);
    }
}
