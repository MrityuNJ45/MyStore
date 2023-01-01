package com.mystore.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mystore.exception.ProductException;
import com.mystore.exception.UserException;
import com.mystore.model.Cart;
import com.mystore.model.CurrentUserSession;
import com.mystore.model.Product;
import com.mystore.model.ProductDTO;
import com.mystore.model.User;
import com.mystore.repository.CartDao;
import com.mystore.repository.CurrentUserSessionDao;
import com.mystore.repository.ProductDao;
import com.mystore.repository.UserDao;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartDao cartDao;
	@Autowired
	private ProductDao productDao;
	@Autowired
	private CurrentUserSessionDao currentUserSessionDao;
	@Autowired
	private UserDao userDao;

	@Override
	public Cart addCart(Cart cart) {

		Cart c = cartDao.save(cart);

		return c;
	}

	@Override
	public Cart addProductToCart(String key, ProductDTO productDTO) throws UserException, ProductException {

		CurrentUserSession cus = currentUserSessionDao.findByUuid(key);
		if(cus == null) {
			throw new UserException("Invalid Key. Please Login First...");
		}
		
		Optional<User> opt = userDao.findById(cus.getUserId());
		if(opt.isPresent()) {
			
			User u = opt.get();
			Cart c = u.getCart();
			
			Optional<Product> optp = productDao.findById(productDTO.getProductId());
			if(!optp.isPresent()) {
				throw new ProductException("Invalid Product Id..");
			}
			
			c.getProducts().add(optp.get());
			int total = 0;
			int q = productDTO.getQuantity();
			
			total = total + q * optp.get().getPrice();
			c.setTotal(c.getTotal() + total);
			
			return cartDao.save(c);
			
			
		}else {
			throw new UserException("Invalid User..");
		}

		
	}

	
	

}
