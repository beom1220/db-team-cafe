package com.example.dbcafe.domain.user.domain;

import lombok.Getter;

@Getter
public enum Level {
    BRONZE("브론즈"),
    SILVER("실버"),
    GOLD("골드"),
    DIAMOND("다이아"),
    VIP("VIP");

    private final String value;

    Level(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
