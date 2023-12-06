package com.example.dbcafe.domain.user.controller;

import com.example.dbcafe.domain.user.service.PrizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/prize")
public class PrizeController {
    private final PrizeService prizeService;
}
