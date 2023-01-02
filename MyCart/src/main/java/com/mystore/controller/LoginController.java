package com.mystore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mystore.exception.LoginException;
import com.mystore.model.LoginDTO;
import com.mystore.service.LoginService;

@RestController
@RequestMapping("/loginservice")
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO,@RequestParam String adminOrUser) throws LoginException {
		
		String s = loginService.login(loginDTO, adminOrUser);
		
		return new ResponseEntity<String>(s, HttpStatus.ACCEPTED);
		
	}
	
	@PostMapping("/logout")
	public ResponseEntity<String> logout (@RequestParam String key) throws LoginException{
		
		
		String s = loginService.logOut(key);
		return new ResponseEntity<String>(s, HttpStatus.ACCEPTED);
		
	}
	
	
}
