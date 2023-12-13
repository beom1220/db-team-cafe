package com.example.dbcafe.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class CouponInfoDto {
    private String name;

    private int discountRatio;

    private int period;

    private int maxDiscount;

    private int maxIssuance;
}
