package com.example.dbcafe.domain.order.domain;

import lombok.Getter;

@Getter
public enum MenuCategory {
    COFFEE("커피"),
    TEA("차"),
    BEVERAGE("음료"),
    SNACK("제과");

    private final String value;

    MenuCategory(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
