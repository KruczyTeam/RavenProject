package com.kruczyteam.raven.User.repository;

import com.kruczyteam.raven.User.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends CrudRepository<User, Long> {
	User findByLogin(String login);
}
