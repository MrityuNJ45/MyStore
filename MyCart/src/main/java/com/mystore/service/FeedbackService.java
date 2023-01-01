package com.mystore.service;

import com.mystore.exception.LoginException;
import com.mystore.exception.ProductException;
import com.mystore.model.Feedback;

public interface FeedbackService {

	public Feedback addFeedback(String key, Feedback feedback) throws LoginException,ProductException;
	
}
