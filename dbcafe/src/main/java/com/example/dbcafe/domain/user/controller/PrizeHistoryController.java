package com.example.dbcafe.domain.user.controller;

import com.example.dbcafe.domain.reservation.dto.EventStatisticsDto;
import com.example.dbcafe.domain.user.dto.PrizeHistoryDto;
import com.example.dbcafe.domain.user.dto.PrizeStatisticsDto;
import com.example.dbcafe.domain.user.service.PrizeHistoryService;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/prize-history")
public class PrizeHistoryController {
    private final PrizeHistoryService prizeHistoryService;

    @GetMapping("/admin")
    public String prizeStatistics(Model model, HttpSession session) {
        String userId = (String) session.getAttribute("loggedInUser");
        if (userId.equals("admin")) {
            PrizeStatisticsDto dto = prizeHistoryService.getStatistic();
            List<PrizeHistoryDto> dtos = prizeHistoryService.showAllHistory();

            model.addAttribute("statistic", dto);
            model.addAttribute("histories", dtos);
            return "admin/prizeStatistics";
        } else {
            return "auth/access-denied";
        }
    }
}
