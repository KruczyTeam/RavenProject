package com.kruczyteam.raven.LoginService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class CredentialsException extends Throwable
{
	public CredentialsException(String message)
	{
		super(message);
	}
}
