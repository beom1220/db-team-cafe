package com.example.dbcafe.domain.reservation.controller;

import com.example.dbcafe.domain.reservation.dto.DayOfReservationBlockDto;
import com.example.dbcafe.domain.reservation.dto.TimeOfReservationBlockDto;
import com.example.dbcafe.domain.reservation.service.ReservationBlockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
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

    @GetMapping("/select-time")
    public String showTimeBlocks(@RequestParam(name = "date", defaultValue = "") LocalDate date, Model model) {
        List<TimeOfReservationBlockDto> times = reservationBlockService.showTimeBlocks(date);

        model.addAttribute("times", times);
        return "reservation/basicTimeForm";
    }
}
