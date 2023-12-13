package com.example.dbcafe.domain.reservation.controller;

import com.example.dbcafe.domain.reservation.domain.Entrant;
import com.example.dbcafe.domain.reservation.domain.Event;
import com.example.dbcafe.domain.reservation.domain.ScheduledEvent;
import com.example.dbcafe.domain.reservation.dto.*;
import com.example.dbcafe.domain.reservation.service.EventService;
import com.example.dbcafe.domain.user.domain.User;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/event")
public class EventController {
    private final EventService eventService;

    @GetMapping("/review")
    public String showReview(@RequestParam(name = "eventId") int eventId, Model model, HttpSession session) {
        session.setAttribute("loggedInUser", "003");
        List<EventReviewDto> dtos = eventService.findReviewsByEventId(eventId);
        boolean isReviewable = eventService.checkReviewable(session, eventId);
        Event event = eventService.findEventById(eventId);

        model.addAttribute("event", event);
        model.addAttribute("isReviewable", isReviewable);
        model.addAttribute("reviews", dtos);
        return "event/review";
    }

    @PostMapping("/review")
    public String addReview(@RequestParam(name = "eventId") int eventId, @ModelAttribute WriteReviewDto dto, HttpSession session) {
        log.info("eventID = " + eventId);
        log.info("dto something = " + dto.getReview());
        log.info("dto rating = " + dto.getRating());
        eventService.addReview(eventId, dto, session);
        return "redirect:/event/review" + "?eventId=" + eventId;
    }

    @GetMapping("/statistics")
    public String eventStatistics(Model model, HttpSession session) {
        String userId = (String) session.getAttribute("loggedInUser");
        if (userId.equals("admin")) {
            List<EventStatisticsDto> dtos = eventService.findStatistics();

            model.addAttribute("events", dtos);
            return "admin/event";
        } else {
            return "auth/access-denied";
        }
    }
}
