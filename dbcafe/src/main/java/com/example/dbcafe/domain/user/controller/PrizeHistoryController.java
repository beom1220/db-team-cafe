package com.example.dbcafe.domain.user.controller;

import com.example.dbcafe.domain.user.service.PrizeHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/prize-history")
public class PrizeHistoryController {
    private final PrizeHistoryService prizeHistoryService;
}
