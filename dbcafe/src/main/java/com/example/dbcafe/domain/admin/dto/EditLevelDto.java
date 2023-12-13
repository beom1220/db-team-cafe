package com.example.dbcafe.domain.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EditLevelDto {
    private int levelIndex;

    private int discountRatio;

    private int cutLine;
}
