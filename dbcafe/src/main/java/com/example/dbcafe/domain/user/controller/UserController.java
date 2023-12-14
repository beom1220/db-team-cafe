package com.example.dbcafe.domain.user.controller;

import com.example.dbcafe.domain.reservation.dto.CouponSelectDto;
import com.example.dbcafe.domain.reservation.service.ReservationService;
import com.example.dbcafe.domain.user.domain.User;
import com.example.dbcafe.domain.user.dto.GiftKeepUserDto;
import com.example.dbcafe.domain.user.dto.KeepUserDto;
import com.example.dbcafe.domain.user.dto.MyPageDto;
import com.example.dbcafe.domain.user.repository.UserRepository;
import com.example.dbcafe.domain.user.dto.PrizeListDto;
import com.example.dbcafe.domain.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final ReservationService reservationService;

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
    public String loginSubmit(@RequestParam("userId") String userId, @RequestParam("userPw") String userPw, Model model, HttpSession session) {
        User user = userService.findById(userId);
        if (userId.equals("admin") && userPw.equals("1111")) {
            session.setAttribute("loggedInUser", "admin");
        } else if (user == null) return "redirect:/user/login";
        else if (user.getPw().equals(userPw)) {
            session.setAttribute("loggedInUser", userId);
        } else {
            return "redirect:/user/login";
        }
        return "redirect:/";
    }

    @GetMapping("/keep")
    public String keepUser(Model model, HttpSession session) {
        User user = userService.findById((String) session.getAttribute("loggedInUser"));
        if (user.getId().equals("admin")) {
            List<KeepUserDto> dtos = userService.findKeepUserInfo();
            List<CouponSelectDto> coupons = reservationService.getCouponList();

            model.addAttribute("coupons", coupons);
            model.addAttribute("userInfos", dtos);
            return "admin/keep-user";
        } else {
            return "auth/access-denied";
        }
    }

    @PostMapping("/keep")
    public String giftForKeepUser(@ModelAttribute GiftKeepUserDto dto) {
        userService.giftForKeepUser(dto);
        return "redirect:/user/keep";
    }
}
