package com.example.dbcafe.domain.reservation.controller;

import com.example.dbcafe.domain.reservation.domain.Entrant;
import com.example.dbcafe.domain.reservation.domain.Event;
import com.example.dbcafe.domain.reservation.dto.EventReviewDto;
import com.example.dbcafe.domain.reservation.dto.WriteReviewDto;
import com.example.dbcafe.domain.reservation.service.EventService;
import com.example.dbcafe.domain.user.domain.User;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/event")
public class EventController {
    private final EventService eventService;

    @GetMapping("/review")
    public String showReview(@RequestParam(name = "eventId") int eventId, Model model, HttpSession session) {
        List<EventReviewDto> dtos = eventService.findReviewsByEventId(eventId);
        boolean isReviewable = eventService.checkReviewable(session, eventId);

        model.addAttribute("isReviewable", isReviewable);
        model.addAttribute("reviews", dtos);
        return "event/review";
    }

    @PostMapping("/review")
    public String addReview(@RequestParam(name = "eventId") int eventId, @ModelAttribute WriteReviewDto dto, HttpSession session) {
        eventService.addReview(eventId, dto, session);
        return "redirect:/event/review";
    }
}
