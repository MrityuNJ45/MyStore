package com.mystore.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mystore.model.Product;

public interface ProductDao extends JpaRepository<Product, Integer>{

	public Optional<Product> findById(Integer id);
	
	public Product findByProductName(String productName);
	
}
