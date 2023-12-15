package com.example.dbcafe.domain.order.service;

import com.example.dbcafe.domain.admin.setting.Setting;
import com.example.dbcafe.domain.admin.setting.SettingRepository;
import com.example.dbcafe.domain.order.domain.*;
import com.example.dbcafe.domain.order.dto.PriceDto;
import com.example.dbcafe.domain.order.dto.reservationSubmitOrderDto;
import com.example.dbcafe.domain.order.repository.OrdersItemRepository;
import com.example.dbcafe.domain.order.repository.OrdersRepository;
import com.example.dbcafe.domain.reservation.domain.ReservationItem;
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
    private final SettingRepository settingRepository;
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

    public PriceDto calcOrderForm(List<CartItem> cartItems, List<OwnCoupon> ownCoupons, ReservationItem item, OwnCoupon ownCoupon, User user){
        PriceDto priceDto = new PriceDto();
        int totalPrice = 0;
        for (CartItem cartItem: cartItems) {
            totalPrice += cartItem.getQuantity()*cartItem.getPrice();
        }
        priceDto.setPrepaymentAmount(item.getPrepaymentAmount());
        priceDto.setTotalPrice(totalPrice);
        priceDto.setCoin(priceDto.getTotalPrice() / 10000);
        priceDto.setEarlybirdDiscountAmount(totalPrice * (item.getEarlybirdDiscountRatio()) / 100);
        priceDto.setWeekdayDiscountAmount(totalPrice * (item.getWeekdayDiscountRatio()) / 100);
        priceDto.setLevelDiscountRatio(settingRepository.findByName(user.getLevel() + "할인율").getValue());
        priceDto.setLevelDiscountAmount(priceDto.getTotalPrice() * priceDto.getLevelDiscountRatio() / 100);

        if(ownCoupon == null){
            priceDto.setCouponDiscountAmount(0);
            priceDto.setCouponDiscountRatio(0);
            priceDto.setOwnCouponId(0);
        }
        else{
            priceDto.setCouponDiscountAmount(totalPrice * ownCoupon.getCoupon().getDiscountRatio() / 100);
            priceDto.setCouponDiscountRatio(ownCoupon.getCoupon().getDiscountRatio());
            priceDto.setOwnCouponId(ownCoupon.getId());
        }
        priceDto.setDiscountAmount(priceDto.getEarlybirdDiscountAmount() + priceDto.getWeekdayDiscountAmount() + priceDto.getCouponDiscountAmount() + priceDto.getLevelDiscountAmount());
        priceDto.setAdditionalAmount(priceDto.getTotalPrice() - priceDto.getDiscountAmount() - priceDto.getPrepaymentAmount());
        return priceDto;
    }

}
