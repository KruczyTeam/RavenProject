package com.kruczyteam.raven.User.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class UserExistException extends RuntimeException
{
	public UserExistException()
	{
		super("User with this login exist in the system");
	}
}
