package com.kruczyteam.raven.User.repository;

import com.kruczyteam.raven.User.model.UserInformation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends CrudRepository<UserInformation, Long> {
	UserInformation findByLogin(String login);
}
