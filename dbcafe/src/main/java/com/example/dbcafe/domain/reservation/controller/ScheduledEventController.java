package com.example.dbcafe.domain.reservation.controller;

import com.example.dbcafe.domain.reservation.domain.ScheduledEvent;
import com.example.dbcafe.domain.reservation.service.ScheduledEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/scheduled-event")
public class ScheduledEventController {
    private final ScheduledEventService scheduledEventService;
}
