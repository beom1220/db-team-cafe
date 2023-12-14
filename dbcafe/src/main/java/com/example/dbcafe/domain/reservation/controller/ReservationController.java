package com.example.dbcafe.domain.reservation.controller;

import com.example.dbcafe.domain.reservation.dto.*;
import com.example.dbcafe.domain.reservation.service.ReservationService;
import com.example.dbcafe.domain.user.domain.Coupon;
import com.example.dbcafe.domain.user.dto.CouponDto;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/reservation")
public class ReservationController {
    private final ReservationService reservationService;

    @GetMapping
    public String reservationForm(@RequestParam(name = "date") LocalDate date, @RequestParam(name = "startTime") LocalTime startTime, @RequestParam(name = "endTime") LocalTime endTime, Model model, HttpSession session) {
        ReservationBlockRequestDto dto = new ReservationBlockRequestDto(date, startTime, endTime);
        List<ReservationBlockResponseDto> responseDtos = reservationService.calDayOfWeekAndDiscountRatio(dto);
        model.addAttribute("reservationBlocks", responseDtos);
        session.setAttribute("blockList", responseDtos);
        return "reservation/form";
    }

    @PostMapping
    public String submitReservation(@ModelAttribute ReservationRequestDto reservationInfo, HttpSession session) {
        log.info("hello");
        List<ReservationBlockResponseDto> dtos = (List<ReservationBlockResponseDto>) session.getAttribute("blockList");
        log.info("dto확인 : " + dtos.get(0).getStartTime());
        session.removeAttribute("blockList");
        reservationService.submitReservation(reservationInfo, dtos, session);
        return "redirect:/";
    }

    @GetMapping("/package")
    public String packageReservationForm(@ModelAttribute PackageReservationBlockDto dto, Model model){
        List<PackageReservationBlockResponseDto> responseDtos = reservationService.calPackageDayOfWeekAndDiscountRatio(dto);
        model.addAttribute("reservationBlocks", responseDtos);
        return "reservation/form";
    }

    @GetMapping("/admin")
    public String showAllReservation(Model model, HttpSession session) {
        // 임시 어드민 로그인
        session.setAttribute("loggedInUser", "admin");

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
        // 임시 어드민 로그인
        session.setAttribute("loggedInUser", "admin");

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
