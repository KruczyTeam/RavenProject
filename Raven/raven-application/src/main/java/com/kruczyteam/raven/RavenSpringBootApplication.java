package com.kruczyteam.raven;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class RavenSpringBootApplication
{
	public static void main(String[] args) throws Exception
	{
		SpringApplication.run(RavenSpringBootApplication.class, args);
	}
}
