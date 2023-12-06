package com.example.dbcafe.domain.order.domain;

import lombok.Getter;

@Getter
public enum PaymentMethod {
    CREDIT("신용카드"),
    CASH("현금"),
    KAKAO("카카오페이");

    private final String value;

    PaymentMethod(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
