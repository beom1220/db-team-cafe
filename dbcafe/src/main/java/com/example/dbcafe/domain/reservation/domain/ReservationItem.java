package com.example.dbcafe.domain.reservation.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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

    private String tempPw;

    private int prepaymentAmount;

    private int earlybirdDiscountRatio;

    private int weekdayDiscountRatio;

    public ReservationItem(Reservation reservation, ReservationBlock reservationBlock, String tempPw, int prepaymentAmount, int earlybirdDiscountRatio, int weekdayDiscountRatio) {
        this.reservation = reservation;
        this.reservationBlock = reservationBlock;
        this.tempPw = tempPw;
        this.prepaymentAmount = prepaymentAmount;
        this.earlybirdDiscountRatio = earlybirdDiscountRatio;
        this.weekdayDiscountRatio = weekdayDiscountRatio;
    }
}
