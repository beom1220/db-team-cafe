package com.example.dbcafe.domain.order.service;

import com.example.dbcafe.domain.order.domain.CartItem;
import com.example.dbcafe.domain.order.repository.CartItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartItemService {
    private final CartItemRepository cartItemRepository;

    public void removeById(int id) {
        CartItem item = cartItemRepository.findCartItemById(id);
        cartItemRepository.delete(item);
    }
}
