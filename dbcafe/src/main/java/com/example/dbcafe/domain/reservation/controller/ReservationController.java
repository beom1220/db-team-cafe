package com.example.dbcafe.domain.reservation.controller;

import com.example.dbcafe.domain.reservation.dto.ReservationBlockRequestDto;
import com.example.dbcafe.domain.reservation.dto.ReservationBlockResponseDto;
import com.example.dbcafe.domain.reservation.dto.ReservationRequestDto;
import com.example.dbcafe.domain.reservation.service.ReservationService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String submitReservation(@ModelAttribute ReservationRequestDto reservationInfo, @ModelAttribute List<ReservationBlockResponseDto> blocks, HttpSession session) {
        reservationService.submitReservation(reservationInfo, blocks, session);
        return "redirect:/";
    }
}
