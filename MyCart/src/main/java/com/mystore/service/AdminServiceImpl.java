package com.mystore.service;

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
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminDao adminDao;
	@Autowired
	private ProductDao productDao;
	@Autowired
	private CurrentUserSessionDao currentUserSessionDao;

	@Override
	public Admin registerAdmin(Admin admin) throws AdminException {
		// TODO Auto-generated method stub

		Admin a = adminDao.findByUsername(admin.getUsername());
		if (a != null) {
			throw new AdminException("Admin already exists...");
		}

		return adminDao.save(admin);

	}

	@Override
	public Product addProduct(String key, Product product) throws ProductException, AdminException, LoginException {
		// TODO Auto-generated method stub

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

	@Override
	public Product getProductById(String key, Integer id) throws ProductException, AdminException, LoginException {
		
		CurrentUserSession cus = currentUserSessionDao.findByUuid(key);
		if (cus == null) {
			throw new LoginException("Admin not logged in..");
		}
		
		Optional<Admin> opt = adminDao.findById(cus.getUserId());
		if (opt.isPresent()) {

			Optional<Product> p = productDao.findById(id);
			if (!p.isPresent()) {
				throw new ProductException("Invalid product id....");
			}

			return p.get();

		}

		throw new AdminException("Invalid Admin...");
		
		
	}

}
