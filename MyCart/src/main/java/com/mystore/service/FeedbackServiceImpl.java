package com.mystore.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.mystore.exception.LoginException;
import com.mystore.exception.ProductException;
import com.mystore.model.CurrentUserSession;
import com.mystore.model.Feedback;
import com.mystore.repository.CurrentUserSessionDao;
import com.mystore.repository.FeedbackDao;
import com.mystore.repository.ProductDao;

public class FeedbackServiceImpl implements FeedbackService {

	@Autowired
	private CurrentUserSessionDao currentUserSessionDao;
	@Autowired
	private FeedbackDao feedbackDao;
	@Autowired
	private ProductDao productDao;

	@Override
	public Feedback addFeedback(String key, Feedback feedback) throws LoginException, ProductException {

		CurrentUserSession cus = currentUserSessionDao.findByUuid(key);
		if (cus == null) {
			throw new LoginException("Please login first....");
		}

		if (productDao.findById(feedback.getProductId()) == null) {
			throw new ProductException("Please enter valid product id...");
		}

		return feedbackDao.save(feedback);

	}

}
