package com.example.dbcafe.domain.order.domain;

import lombok.Getter;

@Getter
public enum OrderStatus {
    PREPARING("준비중"),
    COMPLETE("전달완료"),
    CANCELING("주문취소");

    private final String value;

    OrderStatus(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
