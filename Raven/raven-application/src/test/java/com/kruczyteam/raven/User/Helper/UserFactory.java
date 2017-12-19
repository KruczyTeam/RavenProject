package com.kruczyteam.raven.User.Helper;

import com.kruczyteam.raven.User.model.UserInformation;

public class UserFactory
{
	public static UserInformation getUserWithLogin(String login)
	{
		UserInformation userInformation = new UserInformation();
		userInformation.setLogin(login);
		userInformation.setPassword("password");
		userInformation.setEmail("email");
		userInformation.setRole("ROLE_Admin");

		return userInformation;
	}
}
