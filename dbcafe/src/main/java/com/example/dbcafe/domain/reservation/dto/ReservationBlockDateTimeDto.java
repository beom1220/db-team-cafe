package com.example.dbcafe.domain.reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ReservationBlockDateTimeDto {
    private int id;

    private String dateTime;
}
