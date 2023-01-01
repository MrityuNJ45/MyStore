package com.mystore.service;

import com.mystore.exception.ProductException;
import com.mystore.exception.UserException;
import com.mystore.model.Cart;
import com.mystore.model.ProductDTO;

public interface CartService {

	public Cart addCart(Cart cart);
	
	public Cart addProductToCart(String key, ProductDTO productDTO) throws UserException, ProductException;
	
	
	
}
