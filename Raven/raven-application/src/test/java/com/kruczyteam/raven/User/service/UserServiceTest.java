package com.kruczyteam.raven.User.service;

import com.kruczyteam.raven.History.service.IHistoryService;
import com.kruczyteam.raven.User.Helper.UserFactory;
import com.kruczyteam.raven.User.exception.UserNotFoundException;
import com.kruczyteam.raven.User.model.UserInformation;
import com.kruczyteam.raven.User.repository.IUserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

@RunWith(SpringRunner.class)
public class UserServiceTest
{
	@TestConfiguration
	static class UserServiceTestContextConfiguration
	{
		@Bean
		public IUserService userService()
		{
			return new UserService();
		}
	}

	@Autowired
	private IUserService userService;
	@MockBean
	public IUserRepository userRepository;
	@MockBean
	private IHistoryService historyService;

	final String login = "alex";

	@Before
	public void setUp()
	{
		UserInformation alex = UserFactory.getUserWithLogin(login);
		Mockito.when(userRepository.findByLogin(login)).thenReturn(alex);
	}

	@Test
	public void whenUserIsInDatabaseThenUserIsReturned()
	{
		UserDetails found = null;
		try
		{
			found = userService.getUserByLogin(login);
		} catch (UserNotFoundException e)
		{
			fail("exception not expected");
		}

		assertThat(found.getUsername()).isEqualTo(login);
	}

	@Test
	public void whenUserIsNotInDatabaseThenExceptionIsThrow()
	{
		String otherLogin = "otherLogin";
		Mockito.when(userRepository.findByLogin(otherLogin)).thenReturn(null);

		try
		{
			userService.getUserByLogin(otherLogin);
			fail("exception are expected");
		} catch (UserNotFoundException e)
		{
			return;
		}
	}
}
