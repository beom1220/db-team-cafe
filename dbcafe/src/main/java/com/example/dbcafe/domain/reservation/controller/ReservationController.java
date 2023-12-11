package com.example.dbcafe.domain.reservation.controller;

import com.example.dbcafe.domain.reservation.dto.*;
import com.example.dbcafe.domain.reservation.service.ReservationService;
import com.example.dbcafe.domain.user.domain.Coupon;
import com.example.dbcafe.domain.user.dto.CouponDto;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/reservation")
public class ReservationController {
    private final ReservationService reservationService;

    @GetMapping
    public String reservationForm(@ModelAttribute ReservationBlockRequestDto dto, Model model) {
        List<ReservationBlockResponseDto> responseDtos = reservationService.calDayOfWeekAndDiscountRatio(dto);
        model.addAttribute("reservationBlocks", responseDtos);
        return "reservation/form";
    }

    @PostMapping
    public String submitReservation(@ModelAttribute ReservationRequestDto reservationInfo, HttpSession session) {
        reservationService.submitReservation(reservationInfo, reservationInfo.getBlocks(), session);
        return "redirect:/";
    }

    @GetMapping("/admin")
    public String showAllReservation(Model model, HttpSession session) {
        String userId = (String) session.getAttribute("loggedInUser");
        if (userId.equals("admin")) {
            List<ReservationItemListDto> dtos = reservationService.findAllReservationItem();

            model.addAttribute("reservations", dtos);
            return "admin/reservation";
        } else {
            return "auth/access-denied";
        }
    }

    @GetMapping("/admin-cancel")
    public String cancelForm(@RequestParam("reservationItemId") int reservationItemId, Model model, HttpSession session) {
        String userId = (String) session.getAttribute("loggedInUser");
        if (userId.equals("admin")) {
            RejectionFormDto dto = reservationService.convertToRejectionFormDto(reservationItemId);
            List<CouponSelectDto> coupons = reservationService.getCouponList();

            model.addAttribute("user", dto);
            model.addAttribute("coupons", coupons);
            return "admin/reservationRejectionForm";
        } else {
            return "auth/access-denied";
        }
    }

    @PostMapping("/admin-cancel")
    public String cancel(@ModelAttribute ReservationRejectionDto dto) {
        reservationService.adminRejection(dto);
        return "redirect:/reservation/admin";
    }
}
