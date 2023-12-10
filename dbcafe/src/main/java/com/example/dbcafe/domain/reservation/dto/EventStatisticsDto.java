package com.example.dbcafe.domain.reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EventStatisticsDto {
    private int id;

    private String title;

    private int schedulingCount;

    private int soldOutCount;

    private double rating;
}
