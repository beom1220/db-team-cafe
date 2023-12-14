package com.example.dbcafe.domain.reservation.dto;

import com.example.dbcafe.domain.reservation.domain.Entrant;
import com.example.dbcafe.domain.reservation.domain.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class ScheduledEventDetailDto {
    private int id;

    private String title;

    private int fee;

    private int totalAttendedUser;

    private int totalReviewedUser;

    private double rating;

    private LocalDate date;

    private LocalTime startTime;

    private LocalTime endTime;

    private String content;

    private int capacity;

    private int volunteer;


    private String img;

    private Tag tag;
}
