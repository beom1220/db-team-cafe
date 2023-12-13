package com.example.dbcafe.domain.order.service;

import com.example.dbcafe.domain.order.domain.Cart;
import com.example.dbcafe.domain.order.repository.CartRepository;
import com.example.dbcafe.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;

    public Cart findByUser(User user) {
        return cartRepository.findByUser(user);
    }
}
