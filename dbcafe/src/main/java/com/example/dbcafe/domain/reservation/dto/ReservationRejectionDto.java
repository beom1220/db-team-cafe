package com.example.dbcafe.domain.reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ReservationRejectionDto {
    private String userId;

    private String phone;

    private int couponId;

    private int coin;

    private String content;
}
