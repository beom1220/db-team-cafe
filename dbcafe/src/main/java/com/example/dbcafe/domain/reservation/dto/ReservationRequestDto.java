package com.example.dbcafe.domain.reservation.dto;

import com.example.dbcafe.domain.order.domain.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
public class ReservationRequestDto {
    private String className;

    private int numOfParticipant;

    private String tempPw;

    private int prepaymentTotal;

    private PaymentMethod paymentMethod;
}
