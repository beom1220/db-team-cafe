package com.example.dbcafe.domain.user.controller;

import com.example.dbcafe.domain.user.dto.CouponDto;
import com.example.dbcafe.domain.user.dto.CouponInfoDto;
import com.example.dbcafe.domain.user.dto.PrizeHistoryDto;
import com.example.dbcafe.domain.user.dto.PrizeStatisticsDto;
import com.example.dbcafe.domain.user.service.CouponService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/coupon")
public class CouponController {
    private final CouponService couponService;

    @GetMapping("/form")
    public String form(Model model, HttpSession session) {
        String userId = (String) session.getAttribute("loggedInUser");
        if (userId.equals("admin")) {
            List<CouponDto> dtos = couponService.findAlCoupon();

            model.addAttribute("coupons", dtos);
            return "admin/coupon";
        } else {
            return "auth/access-denied";
        }
    }

    @PostMapping("/form")
    public String add(@ModelAttribute CouponInfoDto dto) {
        couponService.addCoupon(dto);

        return "redirect:/coupon/form";
    }

    @PostMapping("/edit")
    public String edit(@RequestParam("couponId") int couponId, CouponInfoDto dto) {
        couponService.editCoupon(couponId, dto);

        return "redirect:/coupon/form";
    }
}
