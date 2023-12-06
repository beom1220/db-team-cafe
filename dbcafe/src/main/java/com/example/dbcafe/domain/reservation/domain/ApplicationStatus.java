package com.example.dbcafe.domain.reservation.domain;

import lombok.Getter;

@Getter
public enum ApplicationStatus {
    PENDING("검토중"),
    ACCEPTED("수락"),
    REJECTED("거절");

    private final String value;

    ApplicationStatus(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
