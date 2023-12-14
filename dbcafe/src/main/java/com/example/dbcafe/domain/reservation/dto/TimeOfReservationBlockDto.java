package com.example.dbcafe.domain.reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
public class TimeOfReservationBlockDto {
    private int blockId;

    private LocalTime startTime;

    private LocalTime endTime;

    private boolean isBookable;
}
