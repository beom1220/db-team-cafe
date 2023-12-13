package com.example.dbcafe.domain.reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
public class ReservationItemListDto {
    private int id;

    private String className;

    private String name;

    private LocalDate date;

    private LocalTime startTime;

    private boolean isCanceled;
}
