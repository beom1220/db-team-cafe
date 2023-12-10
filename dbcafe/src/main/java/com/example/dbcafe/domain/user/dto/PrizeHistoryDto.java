package com.example.dbcafe.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
public class PrizeHistoryDto {
    private String userId;

    private String prizeName;

    private Date drawedDate;
}
