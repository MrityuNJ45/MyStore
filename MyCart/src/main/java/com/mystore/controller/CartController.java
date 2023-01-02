package com.mystore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mystore.exception.ProductException;
import com.mystore.exception.UserException;
import com.mystore.model.Cart;
import com.mystore.model.ProductDTO;
import com.mystore.service.CartService;

@RestController
@RequestMapping("/cartservice")
public class CartController {

	@Autowired
	private CartService cartService;
	
	@PostMapping("/cart")
	public Cart saveCart(@RequestBody Cart cart) {
		
		return cartService.addCart(cart);
		
	}
	
	@PutMapping("/addtocart/{key}")
	public ResponseEntity<Cart> addProductToCart(@PathVariable("key") String key, @RequestBody ProductDTO productDTO) throws UserException, ProductException  {
		
		Cart c = cartService.addProductToCart(key, productDTO);
		
		return new ResponseEntity<Cart>(c, HttpStatus.ACCEPTED);
		
		
	}
	
	

	
	
}
