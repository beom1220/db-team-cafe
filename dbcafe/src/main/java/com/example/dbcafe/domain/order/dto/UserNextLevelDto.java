package com.example.dbcafe.domain.order.dto;

import com.example.dbcafe.domain.user.domain.Level;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class UserNextLevelDto {
    private String nextLevel;

    private int shortage;
}
