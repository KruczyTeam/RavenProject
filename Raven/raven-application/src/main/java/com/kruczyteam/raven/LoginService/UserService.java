package com.kruczyteam.raven.LoginService;

import org.springframework.stereotype.Service;

@Service
public class UserService implements  IUserService
{
	@Override
	public IUser getUserByCredentials(String name, String password)
	{
		return new User();
	}
}
