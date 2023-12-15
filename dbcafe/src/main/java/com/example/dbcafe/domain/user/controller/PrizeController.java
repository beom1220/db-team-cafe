package com.example.dbcafe.domain.user.controller;

import com.example.dbcafe.domain.user.domain.Prize;
import com.example.dbcafe.domain.user.domain.PrizeHistory;
import com.example.dbcafe.domain.user.domain.User;
import com.example.dbcafe.domain.user.dto.*;
import com.example.dbcafe.domain.user.repository.PrizeHistoryRepository;
import com.example.dbcafe.domain.user.service.PrizeHistoryService;
import com.example.dbcafe.domain.user.service.PrizeService;
import com.example.dbcafe.domain.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/prize")
public class PrizeController {
    private final PrizeService prizeService;
    private final UserService userService;
    private final PrizeHistoryService prizeHistoryService;
    private final PrizeHistoryRepository prizeHistoryRepository;

    @GetMapping
    public String showPrize(Model model, HttpSession session) {
        List<Prize> prizes = prizeService.findAllDrawablePrizes();
        User user = userService.findById((String) session.getAttribute("loggedInUser"));
        PrizeUserInfoDto dto = prizeService.convertToDto(user);
        List<BenefitPrizeHistoryDto> dtos = prizeHistoryService.getBenefitHistory();
        PrizeHistory lastHistory = prizeHistoryRepository.findFirstByUserOrderByCreatedAtDesc(user);

        model.addAttribute("last", lastHistory);
        model.addAttribute("histrories", dtos);
        model.addAttribute("prizes", prizes);
        model.addAttribute("userInfo", dto);
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

    @GetMapping("/addForm")
    public String addForm(Model model, HttpSession session) {
        String userId = (String) session.getAttribute("loggedInUser");
        if (userId.equals("admin")) {
            List<PrizeListDto> prizes = prizeService.findAllPrizes();
            model.addAttribute("prizes", prizes);
            return "admin/prize";
        } else {
            return "auth/access-denied";
        }
    }

    @PostMapping("/addForm")
    public String add(@ModelAttribute PrizeDto dto) {
        prizeService.addPrize(dto);

        return "redirect:/prize/addForm";
    }

    @PostMapping("/edit")
    public String remove(@RequestParam("prizeId") int prizeId, PrizeDto dto) {
        prizeService.editPrize(prizeId, dto);

        return "redirect:/prize/addForm";
    }
}
