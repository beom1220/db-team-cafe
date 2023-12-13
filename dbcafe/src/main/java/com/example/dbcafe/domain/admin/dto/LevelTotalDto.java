package com.example.dbcafe.domain.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LevelTotalDto {
    private int totalUserCount;

    private int totalDiscountAmount;

    private int averageDiscountAmount;
}
