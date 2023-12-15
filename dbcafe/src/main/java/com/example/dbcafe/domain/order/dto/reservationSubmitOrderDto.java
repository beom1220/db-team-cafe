package com.example.dbcafe.domain.order.dto;

import com.example.dbcafe.domain.order.domain.PaymentMethod;
import com.example.dbcafe.domain.user.domain.OwnCoupon;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class reservationSubmitOrderDto {
    private PaymentMethod paymentMethod;
    private int totalPrice;
    private int usedPrepaymentAmount;
    private int weekdayDiscountRatio;
    private int earlybirdDiscountRatio;
    private int levelDiscountRatio;
    private int usedOwnCouponId;
    private int finalPayment;
    private int usedVoucherAmount;
    private int reservationItemId;
}
