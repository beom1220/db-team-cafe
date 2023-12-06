package com.example.dbcafe.domain.reservation.controller;

import com.example.dbcafe.domain.reservation.service.ReservationBlockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/reservation-block")
public class ReservationBlockController {
    private final ReservationBlockService reservationBlockService;
}
