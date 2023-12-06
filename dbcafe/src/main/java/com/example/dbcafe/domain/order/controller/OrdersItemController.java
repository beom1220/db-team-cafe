package com.example.dbcafe.domain.order.controller;

import com.example.dbcafe.domain.order.service.OrdersItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/orders-item")
public class OrdersItemController {
    private final OrdersItemService ordersItemService;
}
