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
    private final DbInitializerService dbInitializerService;
    @GetMapping("/")
    public String index() {

        return "redirect:/search";
    }

    @GetMapping("/search")
    public String search(@RequestParam(name = "keyword", defaultValue = "") String keyword, Model model) {
        List<Menu> menus = menuService.findAllByKeyword(keyword); // 판매 상태가 참인 메뉴 중 검색어 포함된 메뉴 모두 가져옴.
        List<ScheduledEvent> scheduledEvents = scheduledEventService.findAllRecruiting(); // 모집 중인 모든 이벤트 모임 가져옴.
        List<ScheduledEventListDto> dtos = scheduledEventService.convertToListDto(scheduledEvents);
        System.out.println("Menu List:");
        menus.forEach(System.out::println);

        model.addAttribute("menus", menus);
        model.addAttribute("scheduledEvents", dtos);
        return "index";
    }

    @GetMapping("/add-data-button")
    public String addDBButton(){
        dbInitializerService.MenuEntity();
        dbInitializerService.EventEntity();
        dbInitializerService.PlaceEntity();
        dbInitializerService.UserEntity();
        dbInitializerService.SettingEntity();
        dbInitializerService.CouponEntity();
        dbInitializerService.PrizeEntity();
        

        dbInitializerService.SuggestionEntity(); // user 있어야함
        dbInitializerService.MileageHistoryEntity(); // user 있어야함
        dbInitializerService.LevelHistoryEntity(); // user 있어야함
        dbInitializerService.VoucherEntity(); // user 및 menu 있어야함
        dbInitializerService.PrizeHistoryEntity(); // user하고 prize 있어야함
        dbInitializerService.OwnCouponEntity(); // coupon, user 있어야함
        dbInitializerService.ScheduledEventEntity(); // event, place 있어야함
        dbInitializerService.EntrantEntity(); // user, scheduledevent, menu 있어야함

        dbInitializerService.ReservationEntity(); // user 있어야함
        dbInitializerService.ReservationBlockEntity(); // place 있어야함
        dbInitializerService.ReservationChangeRequestEntity(); // reservation 있어야함
        dbInitializerService.ReservationItemEntity(); // reservation, reservation block, setting 있어야함

        dbInitializerService.OrdersEntity(); //user, setting
        dbInitializerService.OrdersItemEntity(); //orders, menu

        dbInitializerService.CartEntity(); // user
        dbInitializerService.CartItemEntity(); // user, menu

        dbInitializerService.ReservationCheckerEntity(); // ReservationBlock 있어야 함.
        return "redirect:/";
    }
}
