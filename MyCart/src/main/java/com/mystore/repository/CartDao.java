package com.mystore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mystore.model.Cart;

public interface CartDao extends JpaRepository<Cart, Integer>{

}
