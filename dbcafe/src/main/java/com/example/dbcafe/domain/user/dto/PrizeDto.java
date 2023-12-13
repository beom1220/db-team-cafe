package com.example.dbcafe.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PrizeDto {
    private String name;

    private boolean isCoin;

    private int value;

    private int probability;
}
