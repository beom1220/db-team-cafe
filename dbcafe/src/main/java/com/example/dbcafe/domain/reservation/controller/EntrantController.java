package com.example.dbcafe.domain.reservation.controller;

import com.example.dbcafe.domain.reservation.service.EntrantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/entrant")
public class EntrantController {
    private final EntrantService entrantService;
}
