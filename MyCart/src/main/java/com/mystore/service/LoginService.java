package com.mystore.service;

import com.mystore.exception.LoginException;
import com.mystore.model.LoginDTO;

public interface LoginService {

	
	public String login(LoginDTO loginDTO, String adminOrUser) throws LoginException;
	
	public String logOut(String key) throws LoginException;
}
