package com.example.dbcafe.domain.order.repository;

import com.example.dbcafe.domain.order.domain.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Orders, Integer> {
}