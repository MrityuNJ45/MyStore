package com.mystore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mystore.model.Orders;

public interface OrdersDao extends JpaRepository<Orders, Integer>{

}
