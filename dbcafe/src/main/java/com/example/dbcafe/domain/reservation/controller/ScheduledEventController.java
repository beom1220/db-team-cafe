package com.example.dbcafe.domain.reservation.controller;

import com.example.dbcafe.domain.reservation.domain.Entrant;
import com.example.dbcafe.domain.reservation.domain.ScheduledEvent;
import com.example.dbcafe.domain.reservation.dto.*;
import com.example.dbcafe.domain.reservation.service.ScheduledEventService;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/entrant-list") //관리자 페이지
    public String entrantList(@RequestParam(name = "scheduledEventId") int scheduledEventId, Model model, HttpSession session) {
        String userId = (String) session.getAttribute("loggedInUser");
        if (userId.equals("admin")) {
            ScheduledEvent scheduledEvent = scheduledEventService.findById(scheduledEventId);
            ScheduledEventDetailDto detailDto = scheduledEventService.convertToDetailDto(scheduledEvent);
            List<EntrantListDto> listDtos = scheduledEventService.findAllEntrant(scheduledEvent);
            model.addAttribute("scheduledEvent", detailDto);
            model.addAttribute("entrants", listDtos);

            return "admin/entrant";
        } else {
            return "auth/access-denied";
        }
    }

    @PostMapping("/entrant/submit")
    public String entrantSubmit(@RequestParam("entrantId") int entrantId) {
        scheduledEventService.submitEntrant(entrantId);
        return "redirect:/entrant-list";
    }

    @PostMapping("/entrant/rejection")
    public String entrantRejection(@RequestParam("entrantId") int entrantId, @RequestParam("rejectionReason") String rejectionReason) {
        scheduledEventService.rejectEntrant(entrantId, rejectionReason);
        return "redirect:/entrant-list";
    }

    @GetMapping("/scheduleForm")
    public String scheduleForm(HttpSession session, Model model) {
        String userId = (String) session.getAttribute("loggedInUser");
        if (userId.equals("admin")) {
            List<EventDto> eventDtos = scheduledEventService.findAllEvent();
            List<ReservationBlockDto> blockDtos = scheduledEventService.findAllBookableBlock();
            model.addAttribute("events", eventDtos);
            model.addAttribute("reservationBlocks", blockDtos);
            return "admin/eventScheduleForm";
        } else {
            return "auth/access-denied";
        }
    }

    @PostMapping("/scheduleForm")
    public String scheduled(@ModelAttribute scheduledDto dto) {
        scheduledEventService.addScheduledEvent(dto);
        return "redirect:/";
    }
}
