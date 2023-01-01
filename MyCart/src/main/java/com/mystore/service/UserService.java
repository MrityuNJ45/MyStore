package com.mystore.service;

import com.mystore.exception.UserException;
import com.mystore.model.User;

public interface UserService {

	public User addUser(User user) throws UserException;
	
	public User updateUser(User user, String key) throws UserException;
	
	public String deleteUser(String key) throws UserException;
	
}
