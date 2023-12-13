package com.example.dbcafe.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class KeepUserDto {
    private String userId;

    private String bestLevel;

    private int acc; // 누적이용금액

    private LocalDate lastReservation;
}
