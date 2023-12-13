package com.example.dbcafe.domain.reservation.dto;

import com.example.dbcafe.domain.user.domain.Level;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RejectionFormDto {
    private String userId;

    private String name;

    private String phone;

    private Level level;
}
