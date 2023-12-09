package com.example.dbcafe.domain.reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class WriteReviewDto {
    private int scheduledEventId;

    private String userId;

    private String review;

    private double rating;
}
