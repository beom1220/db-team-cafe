package com.example.dbcafe.domain.reservation.controller;

import com.example.dbcafe.domain.reservation.service.ReservationItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/reservation-item")
public class ReservationItemController {
    private final ReservationItemService reservationItemService;
}
