package com.kruczyteam.raven;

import com.kruczyteam.raven.LoginService.UserManager;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.util.HashMap;

public class Main
{
	public static void main(String[] args)
    {
	    HashMap<String, Object> props = new HashMap();
	    props.put("server.port", 9999);

	    new SpringApplicationBuilder()
			    .sources(UserManager.class)
			    .properties(props)
			    .run(args);
    }
}
