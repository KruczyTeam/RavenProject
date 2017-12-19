package com.kruczyteam.raven.User.repository;

import com.kruczyteam.raven.User.Helper.UserFactory;
import com.kruczyteam.raven.User.model.UserInformation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTests
{
	@Autowired
	private TestEntityManager entityManager;
	@Autowired
	private IUserRepository userRepository;

	@Before
	public void setUp()
	{
		UserInformation alex = UserFactory.getUserWithLogin("alex");
		entityManager.persist(alex);
		entityManager.flush();
	}

	@Test
	public void whenUserIsInDatabaseThenUserIsReturned()
	{
		String login = "alex";
		UserInformation found = userRepository.findByLogin(login);

		assertThat(found.getLogin()).isEqualTo(login);
	}

	@Test
	public void whenUserIsNotInDatabaseThenNullIsReturned()
	{
		UserInformation found = userRepository.findByLogin("bob");

		assertThat(found).isNull();
	}
}
