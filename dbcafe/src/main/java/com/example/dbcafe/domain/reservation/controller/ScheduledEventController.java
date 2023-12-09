package com.example.dbcafe.domain.reservation.controller;

import com.example.dbcafe.domain.reservation.domain.ScheduledEvent;
import com.example.dbcafe.domain.reservation.dto.EventReviewDto;
import com.example.dbcafe.domain.reservation.dto.ScheduledEventDetailDto;
import com.example.dbcafe.domain.reservation.service.ScheduledEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/scheduled-event")
public class ScheduledEventController {
    private final ScheduledEventService scheduledEventService;


    @GetMapping("/detail")
    public String eventDetail(@RequestParam(name = "id") int id, Model model) {
        ScheduledEvent scheduledEvent = scheduledEventService.findById(id);
        ScheduledEventDetailDto dto = scheduledEventService.convertToDetailDto(scheduledEvent);
        List<EventReviewDto> reviews = scheduledEventService.findReviewsByScheduledEvent(scheduledEvent);

        model.addAttribute("scheduledEvent", dto);
        model.addAttribute("reviews", reviews);

        return "event/detail";
    }
}
