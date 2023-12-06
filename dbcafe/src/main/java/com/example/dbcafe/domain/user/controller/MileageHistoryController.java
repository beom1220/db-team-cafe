package com.example.dbcafe.domain.user.controller;

import com.example.dbcafe.domain.user.service.MileageHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mileage-history")
public class MileageHistoryController {
    private final MileageHistoryService mileageHistoryService;
}
