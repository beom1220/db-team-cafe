package com.example.dbcafe.domain.order.controller;

import com.example.dbcafe.domain.order.service.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cart-item")
public class CartItemController {
    private final CartItemService cartItemService;
}
