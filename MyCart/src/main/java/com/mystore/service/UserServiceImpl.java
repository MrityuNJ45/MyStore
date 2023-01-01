package com.mystore.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mystore.exception.UserException;
import com.mystore.model.Cart;
import com.mystore.model.CurrentUserSession;
import com.mystore.model.User;
import com.mystore.repository.CartDao;
import com.mystore.repository.CurrentUserSessionDao;
import com.mystore.repository.UserDao;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao udao;
	
	@Autowired
	private CartDao cartDao;
	
	@Autowired
	private CurrentUserSessionDao currentUserSessionDao;
	
	@Override
	public User addUser(User user) throws UserException{
		
		if(udao.findByUsername(user.getUsername()) != null) {
			throw new UserException("User Already exists...");
		};
		Cart cart = new Cart();
		cart.setTotal(0);
		user.setCart(cart);
		cartDao.save(cart);
		User a = udao.save(user);
		return a;
		
	}

	@Override
	public User updateUser(User user, String key) throws UserException {
		
		CurrentUserSession validUser = currentUserSessionDao.findByUuid(key);
		if(validUser == null) {
			throw new UserException("User not Logged in...");
		}
		
		Optional<User> opt = udao.findById(validUser.getUserId());
		User u = opt.get();
		u.setAddress(user.getAddress());
		u.setMobile(user.getMobile());
		u.setName(user.getName());
		u.setUsername(user.getUsername());
		u.setPassword(user.getPassword());

		return udao.save(u);
		
		
	}

	@Override
	public String deleteUser(String key) throws UserException {
		
		CurrentUserSession validUser = currentUserSessionDao.findByUuid(key);
		if(validUser == null) {
			throw new UserException("Please Login first...");
		}
		
		Optional<User> opt = udao.findById(validUser.getUserId());
		if(opt.isPresent()) {
			currentUserSessionDao.delete(validUser);
			udao.delete(opt.get());
			return "User Deleted Successfully";
		}
		
		throw new UserException("Not a Valid User...");
	}
	
	
	
	
	

}
