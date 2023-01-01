package com.mystore.service;

import com.mystore.exception.AdminException;
import com.mystore.exception.LoginException;
import com.mystore.exception.ProductException;
import com.mystore.model.Admin;
import com.mystore.model.Product;

public interface AdminService {

	
	public Admin registerAdmin(Admin admin) throws AdminException;
	
	public Product addProduct(String key, Product product) throws ProductException,AdminException,LoginException;
	
	public Product updateProduct(String key, Product product) throws ProductException,AdminException,LoginException;
	
	public Product deleteProduct(String key, Integer id) throws ProductException,AdminException,LoginException;
	
	public Product getProductById(String key, Integer id) throws ProductException,AdminException,LoginException;
	
}
