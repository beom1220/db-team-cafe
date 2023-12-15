package com.example.dbcafe.domain.order.controller;

import com.example.dbcafe.domain.order.domain.Cart;
import com.example.dbcafe.domain.order.domain.CartItem;
import com.example.dbcafe.domain.order.domain.Orders;
import com.example.dbcafe.domain.order.dto.PriceDto;
import com.example.dbcafe.domain.order.dto.UserNextLevelDto;
import com.example.dbcafe.domain.order.dto.reservationSubmitOrderDto;
import com.example.dbcafe.domain.order.service.CartService;
import com.example.dbcafe.domain.order.service.OrdersService;
import com.example.dbcafe.domain.reservation.domain.ReservationItem;
import com.example.dbcafe.domain.reservation.service.ReservationService;
import com.example.dbcafe.domain.user.domain.OwnCoupon;
import com.example.dbcafe.domain.user.domain.User;
import com.example.dbcafe.domain.reservation.dto.reservationOrdersDto;
import com.example.dbcafe.domain.user.repository.OwnCouponRepository;
import com.example.dbcafe.domain.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/orders")
public class OrdersController {
    private final OrdersService ordersService;
    private final CartService cartService;
    private final UserService userService;
    private final ReservationService reservationService;
    private final OwnCouponRepository ownCouponRepository;

    @GetMapping("/find-reservationItem")
    public String findReservationItemForm() {
        return "user/findReservationItemForm";
    }

    @PostMapping("/find-reservationItem")
    public String passMeetingInfo(@ModelAttribute reservationOrdersDto dto, RedirectAttributes redirectAttributes) {
        ReservationItem reservationItem = reservationService
                .findItemByIdAndTempPw(dto.getReservationItemId(), dto.getTempPw());
        if (reservationItem != null) {
            redirectAttributes.addFlashAttribute("reservationItem", reservationItem);
            return "redirect:/orders/meeting-form";
        } else {
            redirectAttributes.addFlashAttribute("failed", true);
            return "redirect:/orders/select-isMeeting";
        }
    }

    @GetMapping("/reservation-form")
    public String reservationForm(Model model, HttpSession session) {
        session.setAttribute("loggedInUser", "001"); // 001 유저로 임시 로그인
        User user = userService.findById((String) session.getAttribute("loggedInUser"));
        Cart cart = cartService.findByUser(user);
        session.setAttribute("reservationItem", reservationService.findItemByIdAndTempPw(5, "1111"));
        ReservationItem item = (ReservationItem) session.getAttribute("reservationItem");
        OwnCoupon ownCoupon = null;
        if(model.containsAttribute("couponId")){
            ownCoupon = ownCouponRepository.findOwnCouponById((int) model.asMap().get("couponId"));
        }
        UserNextLevelDto dto = userService.convertToNextLevelDto(user);

        PriceDto priceDto = ordersService.calcOrderForm(cart.getCartItems(), user.getOwnCoupons(), item, ownCoupon, user);
        model.addAttribute("cartItems", cart.getCartItems());
        model.addAttribute("ownCoupons", user.getOwnCoupons());
        model.addAttribute("reservationItem", item);
        model.addAttribute("levelInfo", dto);
        model.addAttribute("priceDto", priceDto);

        return "user/orderForm";
    }

    @GetMapping("/reservation-button/{couponId}")
    public String reservationButton(@PathVariable int couponId, HttpSession session, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("couponId", couponId);
        log.info(String.valueOf(couponId));
        log.info(String.valueOf(ownCouponRepository.findOwnCouponById(couponId)));
        return "redirect:/orders/reservation-form";
    }

    @PostMapping("/reservation-form")
    public String createOrderByReservation(@ModelAttribute reservationSubmitOrderDto dto, HttpSession session, RedirectAttributes redirectAttributes) {
        User user = userService.findById((String) session.getAttribute("loggedInUser"));
        Orders orders = ordersService.submitReservationOrder(dto, user);
        if (orders == null) {
            redirectAttributes.addFlashAttribute("failedOrder", true);
        }
        return "redirect:/";
    }
}
