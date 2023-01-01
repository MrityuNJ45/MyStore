package com.mystore.service;

import java.util.List;

import com.mystore.exception.AdminException;
import com.mystore.exception.LoginException;
import com.mystore.exception.ProductException;
import com.mystore.model.Product;

public interface ProductService {

	public Product addProduct(String key,Product product) throws ProductException,AdminException,LoginException;
	
	public List<Product> viewAllProduct(String key) throws ProductException,LoginException;
	
	public Product getProductById(String key, Integer id) throws ProductException, LoginException;
	
	public Product updateProduct(String key, Product product) throws ProductException, AdminException, LoginException;
	
	public Product deleteProduct(String key, Integer id) throws ProductException, AdminException, LoginException;
}
