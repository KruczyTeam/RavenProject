package com.kruczyteam.raven.LoginService;

public class UserService implements  IUserService
{
	@Override
	public IUser getUser(String name, String password)
	{
		return new User();
	}
}
