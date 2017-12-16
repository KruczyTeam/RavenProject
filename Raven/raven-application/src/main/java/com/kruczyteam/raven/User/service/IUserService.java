package com.kruczyteam.raven.User.service;

import com.kruczyteam.raven.User.exception.UserExistException;
import com.kruczyteam.raven.User.exception.UserNotFoundException;
import com.kruczyteam.raven.User.model.UserInformation;
import org.springframework.security.core.userdetails.UserDetails;

public interface IUserService
{
	void createUser(UserInformation userInformation) throws UserExistException;

	UserDetails getUserByLogin(String login) throws UserNotFoundException;
}
