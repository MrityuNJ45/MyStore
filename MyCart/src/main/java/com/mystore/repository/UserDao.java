package com.mystore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mystore.model.User;
@Repository
public interface UserDao extends JpaRepository<User, Integer>{

	@Query("select u from User u where u.username = ?1")
	public User findByUsername(String username);
}
