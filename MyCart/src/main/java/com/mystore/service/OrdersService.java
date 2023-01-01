package com.mystore.service;

import javax.persistence.criteria.Order;

import com.mystore.exception.ProductException;
import com.mystore.exception.UserException;
import com.mystore.model.Orders;

public interface OrdersService {

	public String addOrder(String key, String cashOrOnline) throws UserException ;
	
	public Orders getOrder(Integer id);
	
}
