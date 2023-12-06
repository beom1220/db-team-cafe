package com.example.dbcafe.domain.reservation.domain;

import com.example.dbcafe.domain.order.domain.PaymentMethod;
import com.example.dbcafe.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String className;

    private int numOfParticipant;

    private int prepaymentTotal;

    private PaymentMethod paymentMethod;

    private int earlybirdDiscountRatio;

    private int weekdayDiscountRatio;

    private int levelDiscountRatio;

    @Column(nullable = false, name = "created_at")
    @CreatedDate
    private Date createdAt;

    private String cancelReason;

    private boolean isCanceled;

    private int cancellationFeeRatio;

    private int refundAmount;

    private String name;

    private String phone;

    private int changeCount;

    private boolean isPackage;
}
