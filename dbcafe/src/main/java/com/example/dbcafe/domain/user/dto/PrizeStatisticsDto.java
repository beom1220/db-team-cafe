package com.example.dbcafe.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class PrizeStatisticsDto {
    private int totalDrawCount;

    private int totalMileage;

    private int averageMileage;
}
