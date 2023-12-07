package com.example.dbcafe.domain.reservation.controller;

import com.example.dbcafe.domain.reservation.dto.DayOfReservationBlockDto;
import com.example.dbcafe.domain.reservation.service.ReservationBlockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/reservation-block")
public class ReservationBlockController {
    private final ReservationBlockService reservationBlockService;

    @GetMapping
    public String showBasicDays(Model model) {
        List<DayOfReservationBlockDto> days = reservationBlockService.showBasicDays();

        model.addAttribute("days", days);
        return "reservation/basicDateForm";
    }
}
