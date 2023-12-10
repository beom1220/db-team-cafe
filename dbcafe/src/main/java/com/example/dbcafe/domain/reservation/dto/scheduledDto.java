package com.example.dbcafe.domain.reservation.dto;

import com.example.dbcafe.domain.reservation.domain.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Setter
@Getter
@AllArgsConstructor
public class scheduledDto {
    private int eventId;

    private Tag tag;

    private LocalDate date;

    private LocalTime startTime;

    private LocalTime endTime;
}
