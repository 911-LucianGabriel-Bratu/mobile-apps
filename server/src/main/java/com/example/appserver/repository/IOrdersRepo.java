package com.example.appserver.repository;

import com.example.appserver.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrdersRepo extends JpaRepository<Orders, Integer> {
}
