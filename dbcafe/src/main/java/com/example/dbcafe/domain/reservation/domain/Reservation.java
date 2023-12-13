package com.example.dbcafe.domain.reservation.domain;

import com.example.dbcafe.domain.order.domain.PaymentMethod;
import com.example.dbcafe.domain.user.domain.OwnCoupon;
import com.example.dbcafe.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Reservation { // 모임예약
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

    @OneToMany(mappedBy = "reservation", cascade = CascadeType.REMOVE)
    private List<ReservationItem> reservationItems;

    public Reservation(User user, String className, int numOfParticipant, int prepaymentTotal, PaymentMethod paymentMethod, boolean isPackage) {
        this.user = user;
        this.className = className;
        this.numOfParticipant = numOfParticipant;
        this.prepaymentTotal = prepaymentTotal;
        this.paymentMethod = paymentMethod;
        this.isPackage = isPackage;

        this.phone = user.getPhone();
        this.name = user.getName();
        this.changeCount = 0;
    }

    public Reservation(User user, String className, int numOfParticipant, int prepaymentTotal, PaymentMethod paymentMethod, boolean isCanceled, String name, String phone, int changeCount, boolean isPackage) {
        this.user = user;
        this.className = className;
        this.numOfParticipant = numOfParticipant;
        this.prepaymentTotal = prepaymentTotal;
        this.paymentMethod = paymentMethod;
        this.isCanceled = isCanceled;
        this.name = name;
        this.phone = phone;
        this.changeCount = changeCount;
        this.isPackage = isPackage;
    }
}
