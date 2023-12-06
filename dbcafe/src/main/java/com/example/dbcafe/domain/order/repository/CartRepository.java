package com.example.dbcafe.domain.order.repository;

import com.example.dbcafe.domain.order.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer> {
}