package com.mystore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mystore.exception.AdminException;
import com.mystore.exception.LoginException;
import com.mystore.exception.ProductException;
import com.mystore.model.Admin;
import com.mystore.model.CurrentUserSession;
import com.mystore.model.Product;
import com.mystore.repository.AdminDao;
import com.mystore.repository.CurrentUserSessionDao;
import com.mystore.repository.ProductDao;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;

	@Autowired
	private CurrentUserSessionDao currentUserSessionDao;

	@Autowired
	private AdminDao adminDao;

	@Override
	public List<Product> viewAllProduct(String key) throws ProductException, LoginException {

		CurrentUserSession c = currentUserSessionDao.findByUuid(key);
		if (c == null || key == null) {
			throw new LoginException("Please Login First..");
		}

		List<Product> list = productDao.findAll();
		if (list.size() == 0) {
			throw new ProductException("There are no products available...");
		}

		return list;
	}

	@Override
	public Product addProduct(String key, Product product) throws ProductException, AdminException, LoginException {

		CurrentUserSession cus = currentUserSessionDao.findByUuid(key);
		if (cus == null) {
			throw new LoginException("Admin not logged in..");
		}

		Optional<Admin> opt = adminDao.findById(cus.getUserId());
		if (opt.isPresent()) {

			Product p = productDao.findByProductName(product.getProductName());
			if (p != null) {
				throw new ProductException("Product Already Exists...");
			}

			return productDao.save(product);

		}

		throw new AdminException("Invalid Admin...");
	}

	@Override
	public Product getProductById(String key, Integer id) throws ProductException, LoginException {
		CurrentUserSession c = currentUserSessionDao.findByUuid(key);
		if (c == null || key == null) {
			throw new LoginException("Please Login First..");
		}

		Optional<Product> opt = productDao.findById(id);

		if (!opt.isPresent()) {
			throw new ProductException("No Product found by ID : " + id);
		}

		return opt.get();

	}

	@Override
	public Product updateProduct(String key, Product product) throws ProductException, AdminException, LoginException {
		CurrentUserSession cus = currentUserSessionDao.findByUuid(key);
		if (cus == null) {
			throw new LoginException("Admin not logged in..");
		}

		Optional<Admin> opt = adminDao.findById(cus.getUserId());
		if (opt.isPresent()) {

			Optional<Product> p = productDao.findById(product.getProductId());
			if (!p.isPresent()) {
				throw new ProductException("Invalid product to be updated..");
			}

			return productDao.save(product);

		}

		throw new AdminException("Invalid Admin...");
	}

	@Override
	public Product deleteProduct(String key, Integer id) throws ProductException, AdminException, LoginException {
		CurrentUserSession cus = currentUserSessionDao.findByUuid(key);
		if (cus == null) {
			throw new LoginException("Admin not logged in..");
		}

		Optional<Admin> opt = adminDao.findById(cus.getUserId());

		if (opt.isPresent()) {

			Optional<Product> p = productDao.findById(id);
			if (!p.isPresent()) {
				throw new ProductException("Invalid product id to be deleted..");
			}

			Product a = p.get();
			productDao.delete(a);

			return a;
		}

		throw new AdminException("Invalid Admin...");
	}

}
