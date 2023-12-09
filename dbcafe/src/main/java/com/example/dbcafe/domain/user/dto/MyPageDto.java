package com.example.dbcafe.domain.user.dto;

import com.example.dbcafe.domain.reservation.domain.ReservationItem;
import com.example.dbcafe.domain.reservation.domain.ScheduledEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class MyPageDto {
    private String id;

    private String name;

    private String phone;

    private String gender;

    private int mileage;

    private int coin;

    private List<ScheduledEvent> scheduledEventList;

    private List<ReservationItem> reservationItems;
}
