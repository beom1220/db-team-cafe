package com.example.dbcafe.domain.order.controller;

import com.example.dbcafe.domain.order.domain.Cart;
import com.example.dbcafe.domain.order.domain.Orders;
import com.example.dbcafe.domain.order.dto.reservationSubmitOrderDto;
import com.example.dbcafe.domain.order.service.CartService;
import com.example.dbcafe.domain.order.service.OrdersService;
import com.example.dbcafe.domain.reservation.domain.ReservationItem;
import com.example.dbcafe.domain.reservation.service.ReservationService;
import com.example.dbcafe.domain.user.domain.User;
import com.example.dbcafe.domain.reservation.dto.reservationOrdersDto;
import com.example.dbcafe.domain.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrdersController {
    private final OrdersService ordersService;
    private final CartService cartService;
    private final UserService userService;
    private final ReservationService reservationService;

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
        User user = userService.findById((String) session.getAttribute("loggedInUser"));
        Cart cart = cartService.findByUser(user);
        ReservationItem item = (ReservationItem) session.getAttribute("reservationItem");

        model.addAttribute("cartItems", cart.getCartItems());
        model.addAttribute("ownCoupons", user.getOwnCoupons());
        model.addAttribute("reservationItem", item);

        return "user/orderForm";
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
