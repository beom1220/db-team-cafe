package com.example.dbcafe.domain.user.controller;

import com.example.dbcafe.domain.user.domain.Prize;
import com.example.dbcafe.domain.user.domain.User;
import com.example.dbcafe.domain.user.dto.PrizeUserInfoDto;
import com.example.dbcafe.domain.user.service.PrizeService;
import com.example.dbcafe.domain.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/prize")
public class PrizeController {
    private final PrizeService prizeService;
    private final UserService userService;

    @GetMapping
    public String showPrize(Model model, HttpSession session) {
        List<Prize> prizes = prizeService.findAllPrizes();
        User user = userService.findById((String) session.getAttribute("loggedInUser"));
        PrizeUserInfoDto dto = prizeService.convertToDto(user);

        return "user/prize";
    }

    @PostMapping
    public String drawPrize(HttpSession session, RedirectAttributes redirectAttributes) {
        User user = userService.findById((String) session.getAttribute("loggedInUser"));
        if (user.getPrizeChance() == 0 || user.getCoin() == 0) {
            redirectAttributes.addFlashAttribute("noChance", true);
            return "redirect:/prize";
        }
        Prize selectedPrize = prizeService.draw();
        prizeService.settlePrize(selectedPrize, user);
        redirectAttributes.addFlashAttribute("selectedPrize", selectedPrize);
        return "redirect:/prize";
    }
}
