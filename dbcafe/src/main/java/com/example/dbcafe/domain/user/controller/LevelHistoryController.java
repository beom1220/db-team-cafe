package com.example.dbcafe.domain.user.controller;

import com.example.dbcafe.domain.user.service.LevelHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/level-history")
public class LevelHistoryController {
    private final LevelHistoryService levelHistoryService;
}
