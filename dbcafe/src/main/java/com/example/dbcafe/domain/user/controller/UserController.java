package com.example.dbcafe.domain.user.controller;

import com.example.dbcafe.domain.user.domain.User;
import com.example.dbcafe.domain.user.dto.MyPageDto;
import com.example.dbcafe.domain.user.repository.UserRepository;
import com.example.dbcafe.domain.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;

    @GetMapping("/my-page")
    public String myPage(Model model, HttpSession session) {
        User user = userService.findById((String) session.getAttribute("loggedInUser"));
        MyPageDto dto = userService.convertoToMyPageDto(user);

        model.addAttribute("user", dto);
        return "user/myPage";
    }
    @GetMapping("/login")
    public String loginPage() {
        return "user/login";
    }
    @PostMapping("/login")
    public String loginSubmit(@RequestParam("userId") String userId, @RequestParam("userPw") String userPw, Model model, HttpSession session){
        User user = userRepository.findUserById(userId);
        if(userId.equals("admin") && userPw.equals("1111")){
            session.setAttribute("loggedInUser", "admin");
        }
        else if(user == null) return "redirect:/user/login";
        else if(user.getPw().equals(userPw)){
            session.setAttribute("loggedInUser", userId);
        }
        else{
            return "redirect:/user/login";
        }
        return "redirect:/";
    }
}
