package com.kruczyteam.raven.LoginService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1")
public class AuthController implements IAuthManager
{
	@Autowired
	private IUserService userService;

	@RequestMapping(value = "/auth/", method = RequestMethod.GET )
	@ResponseBody
	public String GetUserAuthCode(@RequestHeader(value="UserName") String user, @RequestHeader(value="Password") String password)
	{
		userService.getUserByCredentials(user, password);

		return password;
	}
}
