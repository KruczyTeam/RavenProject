package com.kruczyteam.raven.User.service;

import com.kruczyteam.raven.Backlog.model.Backlog;
import com.kruczyteam.raven.User.exception.UserNotFoundException;
import com.kruczyteam.raven.User.model.User;
import com.kruczyteam.raven.User.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

	private IUserRepository userRepository;

	@Autowired
	public UserService(IUserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public void createUser(User user) {
		userRepository.save(user);
	}

	public String getUserByLogin(String login) throws UserNotFoundException {
		User user = userRepository.findByLogin(login);
		if(user!=null) {
			return user.getPassword();
		}
		 throw new UserNotFoundException();
	}
}
