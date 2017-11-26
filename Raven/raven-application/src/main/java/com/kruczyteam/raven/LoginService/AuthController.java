package com.kruczyteam.raven.LoginService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1")
public class AuthController implements IAuthManager
{
	@Autowired
	private IUserService userService;

	@RequestMapping(value = "/auth/")
	@ResponseBody
	public String GetUserAuthCode(@RequestHeader(value="UserName") String user, @RequestHeader(value="Password") String password)
	{
		userService.getUserByCredentials(user, password);

		return password;
	}
}
