package com.kruczyteam.raven.User.controller;

import com.kruczyteam.raven.User.model.UserInformation;
import com.kruczyteam.raven.User.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/user")
public class UserController
{
	@Autowired
	private UserService userService;

	@PostMapping(value = "/create/")
	public void createUser(@RequestBody UserInformation userInformation)
	{
		userService.createUser(userInformation);
	}

	public void setUserService(UserService userService)
	{
		this.userService = userService;
	}
}
