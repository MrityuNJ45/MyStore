package com.mystore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mystore.model.Feedback;

public interface FeedbackDao extends JpaRepository<Feedback, Integer>{

}
