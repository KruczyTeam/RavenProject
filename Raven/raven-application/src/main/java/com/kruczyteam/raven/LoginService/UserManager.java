package com.kruczyteam.raven.LoginService;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@EnableAutoConfiguration
public class UserManager implements IUserManager
{
	IUserService userService = new UserService();

	@RequestMapping(value = "/LogIn/")
	@ResponseBody
	public String GetUser(@RequestHeader(value="User") String user,@RequestHeader(value="Password") String password)
	{
		userService.getUser(user,password);
		return user+password;
	}
}
