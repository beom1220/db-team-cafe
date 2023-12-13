package com.example.dbcafe.domain.user.controller;

import com.example.dbcafe.domain.user.domain.User;
import com.example.dbcafe.domain.user.dto.KeepUserDto;
import com.example.dbcafe.domain.user.dto.MyPageDto;
import com.example.dbcafe.domain.user.dto.PrizeListDto;
import com.example.dbcafe.domain.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/my-page")
    public String myPage(Model model, HttpSession session) {
        User user = userService.findById((String) session.getAttribute("loggedInUser"));
        MyPageDto dto = userService.convertoToMyPageDto(user);

        model.addAttribute("user", dto);
        return "user/myPage";
    }

    @GetMapping("/keep")
    public String keepUser(Model model, HttpSession session) {
        User user = userService.findById((String) session.getAttribute("loggedInUser"));
        if (user.getId().equals("admin")) {
            List<KeepUserDto> dtos = userService.findKeepUserInfo();

            model.addAttribute("userInfos", dtos);
            return "admin/keep-user";
        } else {
            return "auth/access-denied";
        }
    }
}
