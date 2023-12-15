package com.example.dbcafe.domain.reservation.domain;

import com.example.dbcafe.domain.order.domain.Orders;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class ReservationItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_block_id")
    private ReservationBlock reservationBlock;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Orders orders;

    private String tempPw;

    private int prepaymentAmount;

    private int earlybirdDiscountRatio;

    private int weekdayDiscountRatio;

    private boolean last;

    private boolean keeping;

    private LocalDate date;

    private LocalTime startTime;

    private LocalTime endTime;

    public ReservationItem(Reservation reservation, ReservationBlock reservationBlock, String tempPw, int prepaymentAmount, int earlybirdDiscountRatio, int weekdayDiscountRatio, boolean last) {
        this.reservation = reservation;
        this.reservationBlock = reservationBlock;
        this.tempPw = tempPw;
        this.prepaymentAmount = prepaymentAmount;
        this.earlybirdDiscountRatio = earlybirdDiscountRatio;
        this.weekdayDiscountRatio = weekdayDiscountRatio;
        this.last = last;
        this.keeping = false;
    }
    public ReservationItem(Reservation reservation, ReservationBlock reservationBlock, String tempPw, int prepaymentAmount, int earlybirdDiscountRatio, int weekdayDiscountRatio, boolean last, LocalDate date, LocalTime startTime, LocalTime endTime) {
        this.reservation = reservation;
        this.reservationBlock = reservationBlock;
        this.tempPw = tempPw;
        this.prepaymentAmount = prepaymentAmount;
        this.earlybirdDiscountRatio = earlybirdDiscountRatio;
        this.weekdayDiscountRatio = weekdayDiscountRatio;
        this.last = last;
        this.keeping = false;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
