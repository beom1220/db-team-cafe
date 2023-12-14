package com.example.dbcafe.domain.reservation.controller;

import com.example.dbcafe.domain.reservation.domain.Entrant;
import com.example.dbcafe.domain.reservation.domain.ScheduledEvent;
import com.example.dbcafe.domain.reservation.dto.*;
import com.example.dbcafe.domain.reservation.service.EntrantService;
import com.example.dbcafe.domain.reservation.service.ScheduledEventService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/scheduled-event")
public class ScheduledEventController {
    private final ScheduledEventService scheduledEventService;
    private final EntrantService entrantService;

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
        // 임시로 어드민 로그인 되게끔 하는 코드
        session.setAttribute("loggedInUser", "admin");

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
        Entrant entrant = entrantService.findById(entrantId);
        ScheduledEvent se = entrant.getScheduledEvent();
        log.info("타입은 =" + entrant.getApplicationStatus().getValue());
        return "redirect:/scheduled-event/entrant-list?scheduledEventId=" + se.getId();
    }

    @PostMapping("/entrant/rejection")
    public String entrantRejection(@RequestParam("entrantId") int entrantId, @RequestParam("rejectionReason") String rejectionReason) {
        log.info("entrantId = " + entrantId);
        log.info("reason = " + rejectionReason);
        scheduledEventService.rejectEntrant(entrantId, rejectionReason);
        Entrant entrant = entrantService.findById(entrantId);
        ScheduledEvent se = entrant.getScheduledEvent();
//        return "redirect:/entrant-list";
        return "redirect:/scheduled-event/entrant-list?scheduledEventId=" + se.getId();
    }

    @GetMapping("/scheduleForm")
    public String scheduleForm(HttpSession session, Model model) {
        // 임시 어드민 로그인
        session.setAttribute("loggedInUser", "admin");

        String userId = (String) session.getAttribute("loggedInUser");
        if (userId.equals("admin")) {
            List<EventDto> eventDtos = scheduledEventService.findAllEvent();
            List<ReservationBlockDateTimeDto> blockDtos = scheduledEventService.findAllBookableBlock();
            model.addAttribute("events", eventDtos);
            model.addAttribute("reservationBlocks", blockDtos);
            return "admin/eventScheduleForm";
        } else {
            return "auth/access-denied";
        }
    }

    @PostMapping("/scheduleForm")
    public String scheduled(@ModelAttribute ScheduledDto dto) {
        scheduledEventService.addScheduledEvent(dto);
        return "redirect:/";
    }
}
