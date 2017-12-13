package com.kruczyteam.raven.User.controller;

import com.kruczyteam.raven.User.exception.UserNotFoundException;
import com.kruczyteam.raven.User.model.UserInformation;
import com.kruczyteam.raven.User.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/user")
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping(value = "/create/")
	public void createUser(@RequestBody UserInformation userInformation)
	{
		userService.createUser(userInformation);
	}

	@GetMapping(value = "/retrivepassword/")
	public String retriveUserPassword(@RequestHeader(value="Login") String login) throws UserNotFoundException {
		return userService.getUserByLogin(login).getPassword();
	}
}
