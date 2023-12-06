package com.example.dbcafe.domain.user.domain;

import lombok.Getter;

@Getter
public enum SuggestionCategory {
    CAFE("카페 이용 문의"),
    EVENT("이벤트 모임 문의"),
    WEB("홈페이지 이용 문의"),
    OTHER("기타");

    private final String value;

    SuggestionCategory(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
