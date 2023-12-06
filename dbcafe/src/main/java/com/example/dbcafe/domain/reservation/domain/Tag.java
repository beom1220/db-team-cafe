package com.example.dbcafe.domain.reservation.domain;

import lombok.Getter;

@Getter
public enum Tag {
    NOTHING("해당사항없음"),
    BEST("인기"),
    NEW("신상");

    private final String value;

    Tag(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
