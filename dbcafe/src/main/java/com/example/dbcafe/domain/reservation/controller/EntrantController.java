package com.example.dbcafe.domain.reservation.controller;

import com.example.dbcafe.domain.reservation.domain.ScheduledEvent;
import com.example.dbcafe.domain.reservation.repository.ScheduledEventRepository;
import com.example.dbcafe.domain.reservation.service.EntrantService;
import com.example.dbcafe.domain.user.domain.User;
import com.example.dbcafe.domain.user.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/entrant")
public class EntrantController {
    private final EntrantService entrantService;
    private final UserRepository userRepository;
    private final ScheduledEventRepository scheduledEventRepository;

    @PostMapping("/entry/{scheduledEventId}")
    public String entryTemp(@PathVariable int scheduledEventId, HttpSession session) {
        User user = userRepository.findUserById((String) session.getAttribute("loggedInUser"));
        ScheduledEvent scheduledEvent = scheduledEventRepository.findScheduledEventById(scheduledEventId);
        entrantService.entry(user, scheduledEvent);

        return "redirect:/";
    }
}
