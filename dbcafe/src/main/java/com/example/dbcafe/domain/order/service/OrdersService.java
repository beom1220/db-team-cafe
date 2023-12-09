package com.example.dbcafe.domain.order.service;

import com.example.dbcafe.domain.order.domain.CartItem;
import com.example.dbcafe.domain.order.domain.OrderStatus;
import com.example.dbcafe.domain.order.domain.Orders;
import com.example.dbcafe.domain.order.domain.OrdersItem;
import com.example.dbcafe.domain.order.dto.reservationSubmitOrderDto;
import com.example.dbcafe.domain.order.repository.OrdersItemRepository;
import com.example.dbcafe.domain.order.repository.OrdersRepository;
import com.example.dbcafe.domain.user.domain.Coupon;
import com.example.dbcafe.domain.user.domain.OwnCoupon;
import com.example.dbcafe.domain.user.domain.User;
import com.example.dbcafe.domain.user.service.CouponService;
import com.example.dbcafe.domain.user.service.OwnCouponService;
import com.example.dbcafe.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdersService {
    private final OrdersRepository ordersRepository;
    private final OrdersItemRepository ordersItemRepository;
    private final UserService userService;
    private final OwnCouponService ownCouponService;
    private final CouponService couponService;
    private final CartItemService cartItemService;

    public Orders submitReservationOrder(reservationSubmitOrderDto dto, User user) {
        int weekdayDiscountAmount = (dto.getWeekdayDiscountRatio() * dto.getTotalPrice()) / 100;
        int earlybirdDiscountAmount = (dto.getEarlybirdDiscountRatio() * dto.getTotalPrice()) / 100;
        int levelDiscountAmount = (userService.findLevelDiscountRatio(user.getLevel()) * dto.getTotalPrice()) / 100;
        OwnCoupon ownCoupon = ownCouponService.findById(dto.getUsedOwnCouponId());
        int couponDiscountRatio = ownCoupon.getCoupon().getDiscountRatio();
        Orders orders = new Orders(user, dto.getPaymentMethod(), dto.getTotalPrice(),
                OrderStatus.PREPARING, false, dto.getUsedPrepaymentAmount(),
                dto.getWeekdayDiscountRatio(), weekdayDiscountAmount,
                userService.findLevelDiscountRatio(user.getLevel()), levelDiscountAmount,
                dto.getUsedVoucherAmount(), dto.getFinalPayment());
        Orders savedOrders = ordersRepository.save(orders);
        List<CartItem> items = user.getCart().getCartItems();
        for (CartItem item : items) {
            OrdersItem ordersItem = new OrdersItem(savedOrders, item.getMenu(), item.getQuantity());
            ordersItemRepository.save(ordersItem);
            cartItemService.removeById(item.getId());
        }
        user.setAccumulation(user.getAccumulation() + dto.getTotalPrice());
        int addCoin = dto.getTotalPrice() / 10000;
        user.setCoin(user.getCoin() + addCoin);
        userService.save(user);
        return orders;
    }
}
