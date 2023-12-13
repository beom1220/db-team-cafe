package com.example.dbcafe.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CouponDto {
    private int id;

    private String name;

    private int discountRatio;

    private int period;

    private int maxDiscount;

    private int maxIssuance;

    private int issuance;

    private int useCount;

    private int totalDiscount;
}
