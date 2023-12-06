package com.example.dbcafe.domain.user.controller;

import com.example.dbcafe.domain.user.service.OwnCouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/own-coupon")
public class OwnCouponController {
    private final OwnCouponService ownCouponService;
}
