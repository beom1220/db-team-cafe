package com.example.dbcafe.domain.order.repository;

import com.example.dbcafe.domain.order.domain.Cart;
import com.example.dbcafe.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    Cart findByUser(User user);
}