package com.ashok.auth.services;


import com.ashok.auth.exceptions.UserAlreadyExistsException;
import com.ashok.auth.exceptions.UserNotFoundException;
import com.ashok.auth.model.User;

public interface UserService {

	boolean saveUser(User user) throws UserAlreadyExistsException, UserNotFoundException;

	public User findByUserIdAndPassword(String userId, String password) throws UserNotFoundException;


}
