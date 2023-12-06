package com.example.dbcafe.domain.user.controller;

import com.example.dbcafe.domain.user.service.SuggestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/suggestion")
public class SuggestionController {
    private final SuggestionService suggestionService;
}
