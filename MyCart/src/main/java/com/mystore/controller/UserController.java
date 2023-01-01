package com.mystore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mystore.exception.UserException;
import com.mystore.model.User;
import com.mystore.service.UserService;

@RestController
@RequestMapping("/userservice")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/adduser")
	public ResponseEntity<User> addUser(@RequestBody User user) throws UserException {

		User u = userService.addUser(user);

		return new ResponseEntity<User>(u, HttpStatus.ACCEPTED);

	}

	@PutMapping("/updateuser")
	public ResponseEntity<User> updateUser(@RequestBody User user, @RequestParam String key) throws UserException {

		User u = userService.updateUser(user,key);

		return new ResponseEntity<User>(u, HttpStatus.ACCEPTED);

	}
	
	@DeleteMapping("/deleteuser")
	public ResponseEntity<String> deleteUser(@RequestParam String key) throws UserException{
		
		String s = userService.deleteUser(key);
		return new ResponseEntity<String>(s,HttpStatus.ACCEPTED);
		
	}
	

}
