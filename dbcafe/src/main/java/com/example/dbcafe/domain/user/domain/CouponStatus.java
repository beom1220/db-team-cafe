package com.example.dbcafe.domain.user.domain;

import lombok.Getter;

@Getter
public enum CouponStatus {
    USABLE("사용가능"),
    USED("사용완료"),
    TIMEOVER("기간만료");

    private final String value;

    CouponStatus(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
