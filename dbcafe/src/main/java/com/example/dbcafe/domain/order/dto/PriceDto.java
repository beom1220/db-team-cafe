package com.example.dbcafe.domain.order.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PriceDto {
    private int totalPrice;
    private int discountAmount;
    private int earlybirdDiscountAmount;
    private int weekdayDiscountAmount;
    private int coin;
    private int additionalAmount;
}
