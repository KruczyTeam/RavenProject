package com.kruczyteam.raven.User.Controller;

import com.kruczyteam.raven.GlobalControllerAdvice;
import com.kruczyteam.raven.User.controller.UserController;
import com.kruczyteam.raven.User.exception.UserExistException;
import com.kruczyteam.raven.User.model.UserInformation;
import com.kruczyteam.raven.User.service.UserService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringRunner.class)
@EnableSpringDataWebSupport
public class UserControllerTests
{
	private MockMvc mockMvc;

	String jsonContent = "{\n" + "\t\"login\":\"GKR\",\n" + "\t\"email\":\"gkr@mail.com\",\n" + "\t\"password\":\"test\",\n" + "\t\"role\": \"test\"\n" + "}\n";

	@Before
	public void setup()
	{
		UserController userController = new UserController();
		userController.setUserService(userService);
		this.mockMvc = standaloneSetup(userController).setControllerAdvice(new GlobalControllerAdvice()).build();
	}

	@MockBean
	UserService userService;

	@Test
	public void createUserTest() throws Exception
	{
		doNothing().when(userService).createUser(isA(UserInformation.class));
		this.mockMvc.perform(post("/api/v1/user/create/").content(jsonContent).contentType("application/json")).andExpect(status().isOk());
	}

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void ThrowExceptionWhenUserExist() throws Exception
	{
		doThrow(UserExistException.class).when(userService).createUser(isA(UserInformation.class));

		this.mockMvc.perform(post("/api/v1/user/create/").content(jsonContent).contentType("application/json")).andExpect(status().is(409));
	}
}
