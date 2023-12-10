package com.example.dbcafe.domain.reservation.dto;

import com.example.dbcafe.domain.reservation.domain.ApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EntrantListDto {
    private int id;

    private String name;

    private String phone;

    private String gender;

    private int age;

    private ApplicationStatus applicationStatus;
}
