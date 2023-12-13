package com.example.dbcafe.domain.reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
public class ReservationBlockResponseDto {
    private LocalDate date;

    private String dayOfWeek;

    private LocalTime startTime;

    private LocalTime endTime;

    private int earlybirdDiscountRatio;

    private int weekdayDiscountRatio;
}
