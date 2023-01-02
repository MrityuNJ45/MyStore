package com.mystore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mystore.exception.AdminException;
import com.mystore.exception.LoginException;
import com.mystore.exception.ProductException;
import com.mystore.model.Product;
import com.mystore.service.ProductService;

@RestController
@RequestMapping("/productservice")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping("/viewproducts/{key}")
	public ResponseEntity<List<Product>> viewAllProduct(@PathVariable("key") String key)
			throws ProductException, LoginException {

		List<Product> list = productService.viewAllProduct(key);

		return new ResponseEntity<>(list, HttpStatus.ACCEPTED);

	}

	@PostMapping("/addproduct/{key}")
	public ResponseEntity<Product> addProduct(@PathVariable("key") String key, @RequestBody Product product)
			throws ProductException, AdminException, LoginException {

		Product p = productService.addProduct(key, product);

		return new ResponseEntity<>(p, HttpStatus.ACCEPTED);

	}

	@GetMapping("/getproductbyid/{key}")
	public ResponseEntity<Product> getProductById(@PathVariable("key") String key, @RequestParam Integer productId)
			throws ProductException, LoginException {

		Product p = productService.getProductById(key, productId);

		return new ResponseEntity<>(p, HttpStatus.ACCEPTED);

	}

	@PutMapping("/updateproduct/{key}")
	public ResponseEntity<Product> updateProduct(@PathVariable("key") String key, @RequestBody Product product)
			throws ProductException, AdminException, LoginException {

		Product p = productService.updateProduct(key, product);

		return new ResponseEntity<>(p, HttpStatus.ACCEPTED);

	}
	
	
	@DeleteMapping("/deleteproductbyid/{key}")
	public ResponseEntity<Product> deleteProductById(@PathVariable("key") String key, @RequestParam Integer productId)
			throws ProductException, LoginException, AdminException {

		Product p = productService.deleteProduct(key, productId);

		return new ResponseEntity<>(p, HttpStatus.ACCEPTED);

	}
	
	

}
