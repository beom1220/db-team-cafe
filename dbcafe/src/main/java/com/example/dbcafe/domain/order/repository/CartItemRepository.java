package com.example.dbcafe.domain.order.repository;

import com.example.dbcafe.domain.order.domain.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
    CartItem findCartItemById(int id);
}