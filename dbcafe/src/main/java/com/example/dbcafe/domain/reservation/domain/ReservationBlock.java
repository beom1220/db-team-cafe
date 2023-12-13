package com.example.dbcafe.domain.reservation.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class ReservationBlock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
    private Place place;

    private LocalDate date;

    private LocalTime startTime;

    private LocalTime endTime;

    private boolean isBookable;

    private DayOfWeek dayOfWeek;

    public boolean getIsBookable() {
        return isBookable;
    }

    public ReservationBlock(Place place, LocalDate date, LocalTime startTime, LocalTime endTime, boolean isBookable) {
        this.place = place;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isBookable = isBookable;
        this.dayOfWeek = date.getDayOfWeek();
    }
}
