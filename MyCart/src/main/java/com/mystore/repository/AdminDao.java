package com.mystore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mystore.model.Admin;

public interface AdminDao extends JpaRepository<Admin, Integer>{
	@Query("select a from Admin a where a.username = ?1")
	public Admin findByUsername(String username);
	
	
}
