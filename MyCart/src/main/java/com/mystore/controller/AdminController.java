package com.mystore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mystore.exception.AdminException;
import com.mystore.model.Admin;
import com.mystore.service.AdminService;

@RestController
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	
	@PostMapping("/registeradmin")
	public ResponseEntity<Admin> registerAdmin(@RequestBody Admin admin) throws AdminException{
		
		 Admin a  =   adminService.registerAdmin(admin);
		
		 return new ResponseEntity<Admin>(a,HttpStatus.ACCEPTED);
		
		
	}
	
	
	
}
