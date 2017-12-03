package com.kruczyteam.raven.LoginService;

public interface IUserService
{
	IUser getUserByCredentials(String name, String password);
}
