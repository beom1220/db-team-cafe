package com.example.dbcafe.controller;

import com.example.dbcafe.domain.order.domain.Menu;
import com.example.dbcafe.domain.order.service.MenuService;
import com.example.dbcafe.domain.reservation.domain.ScheduledEvent;
import com.example.dbcafe.domain.reservation.dto.ScheduledEventListDto;
import com.example.dbcafe.domain.reservation.service.ScheduledEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final MenuService menuService;
    private final ScheduledEventService scheduledEventService;

    @GetMapping("/")
    public String index() {
        return "redirect:/search";
    }

    @GetMapping("/search")
    public String search(@RequestParam(name = "keyword", defaultValue = "") String keyword, Model model) {
        List<Menu> menus = menuService.findAllByKeyword(keyword); // 판매 상태가 참인 메뉴 중 검색어 포함된 메뉴 모두 가져옴.
        List<ScheduledEvent> scheduledEvents = scheduledEventService.findAllRecruiting(); // 모집 중인 모든 이벤트 모임 가져옴.
        List<ScheduledEventListDto> dtos = scheduledEventService.convertToListDto(scheduledEvents);

        model.addAttribute("menus", menus);
        model.addAttribute("scheduledEvents", dtos);
        return "index";
    }
}
