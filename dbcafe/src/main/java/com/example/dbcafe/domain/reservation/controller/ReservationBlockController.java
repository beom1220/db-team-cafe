package com.example.dbcafe.domain.reservation.controller;

import com.example.dbcafe.domain.reservation.domain.DayOfWeekInKorean;
import com.example.dbcafe.domain.reservation.dto.DayOfReservationBlockDto;
import com.example.dbcafe.domain.reservation.dto.PackageReservationBlockDto;
import com.example.dbcafe.domain.reservation.dto.ReservationBlockRequestDto;
import com.example.dbcafe.domain.reservation.dto.TimeOfReservationBlockDto;
import com.example.dbcafe.domain.reservation.dto.UserSelectDayDto;
import com.example.dbcafe.domain.reservation.service.ReservationBlockService;
import com.example.dbcafe.domain.user.domain.User;
import com.example.dbcafe.domain.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/reservation-block")
public class ReservationBlockController {
    private final ReservationBlockService reservationBlockService;
    private final UserService userService;

    @GetMapping
    public String showBasicDays(Model model, HttpSession session) {
        List<DayOfReservationBlockDto> days = reservationBlockService.showBasicDays();

        User user = userService.findById((String) session.getAttribute("loggedInUser"));
        UserSelectDayDto dto = userService.convertToSelectDayDto(user);

        model.addAttribute("userInfo", dto);
        model.addAttribute("days", days);
        return "reservation/basicDateForm";
    }

    @GetMapping("/select-time")
    public String showTimeBlocks(@RequestParam(name = "date", defaultValue = "") LocalDate date, Model model) {
        List<TimeOfReservationBlockDto> times = reservationBlockService.showTimeBlocks(date);
        for (TimeOfReservationBlockDto d : times) {
            log.info("시간 : " + d.getStartTime());
        }
        String dayOfWeek = DayOfWeekInKorean.valueOf(date.getDayOfWeek().name()).getDay();

        model.addAttribute("day", dayOfWeek);
        model.addAttribute("date", date);
        model.addAttribute("times", times);
        return "reservation/basicTimeForm";
    }

    @GetMapping("/package")
    public String showAllPackages(Model model) {
        List<PackageReservationBlockDto> dtos = reservationBlockService.findAllPackagesAndConvertToDto();
        model.addAttribute("packages", dtos);

        return "reservation/packageDateForm";
    }

    @GetMapping("/package/search")
    public String searchPackages(Model model, @RequestParam(name = "dayOfWeek", defaultValue = "") String dayOfWeek,
                                 @RequestParam(name = "startTime", defaultValue = "") String startTime) {
        List<PackageReservationBlockDto> dtos = reservationBlockService.searchPackagesAndConvertToDto(dayOfWeek, startTime);
        model.addAttribute("packages", dtos);

        return "reservation/packageDateForm";
    }

    @PostMapping("/select-time")
    public String setBlocks(@RequestParam(name = "date", defaultValue = "") LocalDate date, @ModelAttribute ReservationBlockRequestDto dto) {
        return "redirect:/reservation?date=" + date + "&startTime=" + dto.getStartTime() + "&endTime=" + dto.getEndTime();
    }
}
