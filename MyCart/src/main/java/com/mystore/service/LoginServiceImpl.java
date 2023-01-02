package com.mystore.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mystore.exception.LoginException;
import com.mystore.model.Admin;
import com.mystore.model.CurrentUserSession;
import com.mystore.model.LoginDTO;
import com.mystore.model.User;
import com.mystore.repository.AdminDao;
import com.mystore.repository.CurrentUserSessionDao;
import com.mystore.repository.UserDao;

import net.bytebuddy.utility.RandomString;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private CurrentUserSessionDao currentUserSessionDao;

	@Autowired
	private AdminDao adminDao;

	@Override
	public String login(LoginDTO loginDTO, String adminOrUser) throws LoginException {

		if (adminOrUser.equals("user")) {
			User user = userDao.findByUsername(loginDTO.getUsername());
			if (user == null) {
				throw new LoginException("Please provide valid details..");
			}

			Optional<CurrentUserSession> opt = currentUserSessionDao.findById(user.getUserId());

			if (opt.isPresent()) {
				throw new LoginException("User already Logged in..");
			}

			if (loginDTO.getPassword().equals(user.getPassword())) {

				CurrentUserSession newLogger = new CurrentUserSession();
				newLogger.setUserId(user.getUserId());
				newLogger.setLoginTime(LocalDateTime.now());
				String key = RandomString.make(4);
				newLogger.setUuid(key);
				return currentUserSessionDao.save(newLogger).toString();

			} else {
				throw new LoginException("Invalid Credentials...");
			}
		} else if (adminOrUser.equals("admin")) {

			Admin admin = adminDao.findByUsername(loginDTO.getUsername());
			if (admin == null) {
				throw new LoginException("Please provide valid details..");
			}

			Optional<CurrentUserSession> opt = currentUserSessionDao.findById(admin.getAdminId());

			if (opt.isPresent()) {
				throw new LoginException("User already Logged in..");
			}

			if (loginDTO.getPassword().equals(admin.getPassword())) {

				CurrentUserSession newLogger = new CurrentUserSession();
				newLogger.setUserId(admin.getAdminId());
				newLogger.setLoginTime(LocalDateTime.now());
				String key = RandomString.make(4);
				newLogger.setUuid(key);
				return currentUserSessionDao.save(newLogger).toString();

			} else {
				throw new LoginException("Invalid Credentials...");
			}

		}

		throw new LoginException("Invalid Request...");

	}

	@Override
	public String logOut(String key) throws LoginException {

		CurrentUserSession existingUser = currentUserSessionDao.findByUuid(key);

		if (existingUser == null) {
			throw new LoginException("Invalid User Key...");
		}

		currentUserSessionDao.delete(existingUser);

		return "Logged Out Successfully...";
	}

}
