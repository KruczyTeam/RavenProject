package com.kruczyteam.raven.Backlog.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BacklogControllerAdvice
{
    @ExceptionHandler(BacklogNotFoundException.class)
    public Exception notFoundException(final BacklogNotFoundException e)
    {
        return e;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public Exception assertionException(final IllegalArgumentException e)
    {
        return e;
    }
}
