package com.kruczyteam.raven.User.service;

import com.kruczyteam.raven.History.Enums.Operation;
import com.kruczyteam.raven.History.Enums.Services;
import com.kruczyteam.raven.History.service.IHistoryService;
import com.kruczyteam.raven.User.exception.UserExistException;
import com.kruczyteam.raven.User.exception.UserNotFoundException;
import com.kruczyteam.raven.User.model.UserInformation;
import com.kruczyteam.raven.User.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserService implements UserDetailsService, IUserService
{
	private IUserRepository userRepository;
	private IHistoryService historyService;
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	@Autowired
	public UserService(IUserRepository userRepository, IHistoryService historyService)
	{
		this.userRepository = userRepository;
		this.historyService = historyService;
	}

	public void createUser(UserInformation userInformation) throws UserExistException
	{
		UserInformation user = userRepository.findByLogin(userInformation.getLogin());
		if (user == null)
		{
			userInformation.setPassword(encoder.encode(userInformation.getPassword()));
			userInformation.setRole("ROLE_User");
			user = userRepository.save(userInformation);
			historyService.AddToHistory(Services.User, Operation.Add, user.getId().toString());
		} else
		{
			throw new UserExistException();
		}
	}

	public UserDetails getUserByLogin(String login) throws UserNotFoundException
	{
		UserInformation userInformation = userRepository.findByLogin(login);

		if (userInformation != null)
		{
			GrantedAuthority authority = new SimpleGrantedAuthority(userInformation.getRole());
			UserDetails userDetails = new User(userInformation.getLogin(), userInformation.getPassword(), Arrays.asList(authority));
			return userDetails;
		}
		throw new UserNotFoundException();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		try
		{
			return getUserByLogin(username);
		} catch (UserNotFoundException e)
		{
			throw new UsernameNotFoundException("userName");
		}
	}
}
