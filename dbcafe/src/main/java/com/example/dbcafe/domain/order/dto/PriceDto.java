package com.example.dbcafe.domain.order.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PriceDto {
    private int totalPrice;
    private int discountAmount;
    private int prepaymentAmount;
    private int earlybirdDiscountAmount;
    private int additionalAmount;
    private int levelDiscountAmount;
    private int levelDiscountRatio;
    private int weekdayDiscountAmount;
    private int couponDiscountAmount;
    private int couponDiscountRatio;
    private int ownCouponId;
    private int coin;
}
