package com.example.dbcafe.domain.admin.setting;

import com.example.dbcafe.domain.admin.dto.EditLevelDto;
import com.example.dbcafe.domain.admin.dto.LevelDto;
import com.example.dbcafe.domain.admin.dto.LevelTotalDto;
import com.example.dbcafe.domain.user.dto.PrizeHistoryDto;
import com.example.dbcafe.domain.user.dto.PrizeStatisticsDto;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/setting")
public class SettingController {
    private final SettingService settingService;

    @GetMapping("/level")
    public String levelInfo(Model model, HttpSession session) {
        // 임시 어드민 로그인
        session.setAttribute("loggedInUser", "admin");

        String userId = (String) session.getAttribute("loggedInUser");
        if (userId.equals("admin")) {
            List<LevelDto> dtos = settingService.getLevelInfo();
            LevelTotalDto dto = settingService.getTotalLevelInfo(dtos);

            model.addAttribute("levels", dtos);
            model.addAttribute("total", dto);
            return "admin/level";
        } else {
            return "auth/access-denied";
        }
    }

    @PostMapping("/level")
    public String editLevel(@ModelAttribute EditLevelDto dto) {
        settingService.editLevel(dto);
        return "redirect:/level";
    }
}
