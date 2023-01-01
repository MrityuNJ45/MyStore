package com.mystore.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.criteria.Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mystore.exception.UserException;
import com.mystore.model.Cart;
import com.mystore.model.CurrentUserSession;
import com.mystore.model.Orders;
import com.mystore.model.Product;
import com.mystore.model.User;
import com.mystore.repository.CartDao;
import com.mystore.repository.CurrentUserSessionDao;
import com.mystore.repository.OrdersDao;
import com.mystore.repository.UserDao;

@Service
public class OrdersServiceImpl implements OrdersService {

	@Autowired
	private OrdersDao orderDao;

	@Autowired
	private CartDao cartDao;

	@Autowired
	private CurrentUserSessionDao currentUserSessionDao;

	@Autowired
	private UserDao userDao;

	@Override
	public String addOrder(String key, String cashOrOnline) throws UserException {
		
		CurrentUserSession validUser = currentUserSessionDao.findByUuid(key);
		if (validUser == null) {
			throw new UserException("Please Login First...");
		}

		Optional<User> opt = userDao.findById(validUser.getUserId());
		
		if(opt.isPresent()) {
			
			Cart c = opt.get().getCart();
			
			Orders order = new Orders();
			order.setOrderDate(LocalDate.now());
			order.setPaymentOption(cashOrOnline);
			
			
			
			order.setTotal(c.getTotal());
			

			c.setProducts(new HashSet<>()) ; 
			c.setTotal(0);
			cartDao.save(c);
			opt.get().getOrders().add(order);
			userDao.save(opt.get());
			Orders placedO = orderDao.save(order);
			
			return "Order Has been placed, Order Id : " + placedO.getOrderId();
		}
		

		throw new UserException("Invalid User..");

	}

	@Override
	public Orders getOrder(Integer id) {
		
		Optional<Orders> o = orderDao.findById(id);
		
		return o.get();
	}
	
	
	

}
