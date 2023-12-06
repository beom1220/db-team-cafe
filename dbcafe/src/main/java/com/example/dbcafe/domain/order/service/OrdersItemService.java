package com.example.dbcafe.domain.order.service;

import com.example.dbcafe.domain.order.repository.OrdersItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrdersItemService {
    private final OrdersItemRepository ordersItemRepository;
}

