package com.example.dbcafe.domain.user.controller;

import com.example.dbcafe.domain.user.domain.Suggestion;
import com.example.dbcafe.domain.user.domain.User;
import com.example.dbcafe.domain.user.dto.SuggestionDto;
import com.example.dbcafe.domain.user.service.SuggestionService;
import com.example.dbcafe.domain.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/suggestion")
public class SuggestionController {
    private final SuggestionService suggestionService;
    private final UserService userService;

    @GetMapping
    public String showSuggestion(Model model, HttpSession session) {
        User user = userService.findById((String) session.getAttribute("loggedInUser"));
        List<Suggestion> suggestions = suggestionService.findAllByUser(user);

        model.addAttribute("suggestions", suggestions);
        return "user/suggestion";
    }

    @PostMapping
    public String addSuggestion(@ModelAttribute SuggestionDto dto, HttpSession session) {
        User user = userService.findById((String) session.getAttribute("loggedInUser"));
        suggestionService.addSuggestion(dto, user);
        return "redirect:/suggestion";
    }
}
