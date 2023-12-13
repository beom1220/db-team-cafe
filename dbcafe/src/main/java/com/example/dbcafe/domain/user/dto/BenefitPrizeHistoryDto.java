package com.example.dbcafe.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class BenefitPrizeHistoryDto {
    private String userId;

    private String prizeName;

    private Date date;
}
