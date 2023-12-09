package com.example.dbcafe.domain.user.controller;

import com.example.dbcafe.domain.user.domain.User;
import com.example.dbcafe.domain.user.dto.MyPageDto;
import com.example.dbcafe.domain.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
