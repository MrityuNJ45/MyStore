package com.mystore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mystore.exception.UserException;
import com.mystore.model.Orders;
import com.mystore.service.OrdersService;

@RestController
@RequestMapping("/orderservice")
public class OrdersController {

	@Autowired
	private OrdersService orderService;
	
	@PostMapping("/addorder/{key}")
	public ResponseEntity<String> addOrder(@PathVariable("key") String key, @RequestParam String paymentType ) throws UserException {
		
		String o = orderService.addOrder(key,paymentType);
		
		return new ResponseEntity<String>(o,HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/getorder/{id}")
	public ResponseEntity<Orders> getOrder(@PathVariable("id") Integer id ){
		
		
		Orders o = orderService.getOrder(id);

		return new ResponseEntity<Orders>(o,HttpStatus.ACCEPTED);
		
	}
	
	
	
	
}
