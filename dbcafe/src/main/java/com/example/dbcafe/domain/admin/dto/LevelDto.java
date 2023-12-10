package com.example.dbcafe.domain.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class LevelDto {
    private String name;

    private int userCount;

    private int totalDiscountAmount;

    private int averageDiscountAmount;

    private int discountRatio;

    private int cutLine;
}
