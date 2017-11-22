package com.kruczyteam.raven.LoginService;

import org.springframework.http.ResponseEntity;

public interface IAuthManager
{
	String GetUserAuthCode(String userName, String password);
}
