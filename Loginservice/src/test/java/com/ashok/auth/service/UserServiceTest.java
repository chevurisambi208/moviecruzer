package com.ashok.auth.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ashok.auth.exceptions.UserAlreadyExistsException;
import com.ashok.auth.exceptions.UserNotFoundException;
import com.ashok.auth.model.User;
import com.ashok.auth.repositories.UserRepository;
import com.ashok.auth.services.UserServiceImpl;

public class UserServiceTest {

	@Mock
	private UserRepository userRepository;

	private User user;

	@InjectMocks
	private UserServiceImpl userServiceImpl;

	Optional<User> options;

	@Before
	public void setUp() {

		MockitoAnnotations.initMocks(this);
		user = new User("sonu3706", "Chandan", "Mishra", "123456", new Date());
		options = Optional.of(user);
	}

	@Test
	public void testSaveUserSuccess() throws UserNotFoundException, UserAlreadyExistsException {

		when(userRepository.save(user)).thenReturn(user);
		boolean flag = userServiceImpl.saveUser(user);
		assertEquals("Cannot Register User", true, flag);
		verify(userRepository, times(1)).save(user);
	}

	@Test(expected = UserAlreadyExistsException.class)
	public void testSaveUserFailure() throws UserNotFoundException, UserAlreadyExistsException {
		when(userRepository.findById(user.getUserId())).thenReturn(options);
		when(userRepository.save(user)).thenReturn(user);
		boolean flag = userServiceImpl.saveUser(user);

	}

	@Test
	public void testValidateSuccess() throws UserNotFoundException {

		when(userRepository.findByUserIdAndPassword(user.getUserId(), user.getPassword())).thenReturn(user);
		User userResult = userServiceImpl.findByUserIdAndPassword(user.getUserId(), user.getPassword());
		assertNotNull(userResult);
		assertEquals("sonu3706", userResult.getUserId());
		verify(userRepository, times(1)).findByUserIdAndPassword(user.getUserId(), user.getPassword());

	}

	@Test(expected = UserNotFoundException.class)
	public void testValidateFailure() throws UserNotFoundException {

		when(userRepository.findById("sonu")).thenReturn(null);
		userServiceImpl.findByUserIdAndPassword(user.getUserId(), user.getPassword());

	}

}
