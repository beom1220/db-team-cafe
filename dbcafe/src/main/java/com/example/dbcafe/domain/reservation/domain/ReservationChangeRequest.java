package com.example.dbcafe.domain.reservation.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class ReservationChangeRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

    private LocalDate newDate;

    private LocalTime newStartTime;

    private LocalTime newEndTime;

    private boolean isAccepted;

    private String rejectionReason;

    public ReservationChangeRequest(Reservation reservation, LocalDate newDate, LocalTime newStartTime, LocalTime newEndTime, boolean isAccepted, String rejectionReason) {
        this.reservation = reservation;
        this.newDate = newDate;
        this.newStartTime = newStartTime;
        this.newEndTime = newEndTime;
        this.isAccepted = isAccepted;
        this.rejectionReason = rejectionReason;
    }
}
