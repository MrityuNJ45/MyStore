package com.mystore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mystore.model.CurrentUserSession;

public interface CurrentUserSessionDao extends JpaRepository<CurrentUserSession, Integer>{

	public CurrentUserSession findByUuid(String key);
	
	
}
