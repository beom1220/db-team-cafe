package com.example.dbcafe.domain.reservation.dto;

import com.example.dbcafe.domain.reservation.domain.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
public class ScheduledEventListDto {
    private int id;

    private String title;

    private double rating;

    private LocalDate date;

    private LocalTime startTime;

    private LocalTime endTime;

    private int capacity;

    private int volunteer;

    private String img;

    private Tag tag;
}
