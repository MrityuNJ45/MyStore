package com.mystore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mystore.exception.LoginException;
import com.mystore.exception.ProductException;
import com.mystore.model.Feedback;
import com.mystore.service.FeedbackService;

@RestController
public class FeedBackController {

	@Autowired
	private FeedbackService feedbackService;
	
	@PostMapping("/addfeedback/")
	public ResponseEntity<Feedback> addFeedBack(@RequestParam String key,@RequestBody Feedback feedback) throws LoginException, ProductException{
		
		Feedback fb = feedbackService.addFeedback( key, feedback);
		return new ResponseEntity<Feedback>(fb,HttpStatus.ACCEPTED);
		
		
	}
	
}
