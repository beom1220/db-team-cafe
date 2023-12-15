package com.example.dbcafe.domain.order.domain;

import com.example.dbcafe.domain.user.domain.Level;
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
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    private int totalPrice;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private boolean isTakeout;

    private int usedPrepaymentAmount;

    private boolean isFinished;

    @Column(nullable = false, name = "created_at")
    @CreatedDate
    private Date createdAt;

    private int weekdayDiscountRatio;

    private int weekdayDiscountAmount;

    private int levelDiscountRatio;

    private int levelDiscountAmount;

    private int usedVoucherAmount;

    private int remainingTime;

    private int finalPayment;

    private int couponDiscountRatio;

    private int couponDiscountAmount;

    private Date finishedTime;

    private Level level;

    public Orders(User user, PaymentMethod paymentMethod, int totalPrice,
                  OrderStatus orderStatus, boolean b, int usedPrepaymentAmount,
                  int weekdayDiscountRatio, int weekdayDiscountAmount, int levelDiscountRatio,
                  int levelDiscountAmount, int usedVoucherAmount, int finalPayment) {
        this.user = user;
        this.paymentMethod = paymentMethod;
        this.totalPrice = totalPrice;
        this.orderStatus = orderStatus;
        this.isTakeout = b;
        this.usedPrepaymentAmount = usedPrepaymentAmount;
        this.weekdayDiscountRatio = weekdayDiscountRatio;
        this.weekdayDiscountAmount = weekdayDiscountAmount;
        this.levelDiscountRatio = levelDiscountRatio;
        this.levelDiscountAmount = levelDiscountAmount;
        this.usedVoucherAmount = usedVoucherAmount;
        this.finalPayment = finalPayment;
        this.level = user.getLevel();
    }

    public Orders(User user, PaymentMethod paymentMethod, int totalPrice,
                  OrderStatus orderStatus, boolean b, int usedPrepaymentAmount,
                  int weekdayDiscountRatio, int weekdayDiscountAmount, int levelDiscountRatio,
                  int levelDiscountAmount, int usedVoucherAmount, int finalPayment,
                  int couponDiscountRatio, int couponDiscountAmount) {
        this.user = user;
        this.paymentMethod = paymentMethod;
        this.totalPrice = totalPrice;
        this.orderStatus = orderStatus;
        this.isTakeout = b;
        this.usedPrepaymentAmount = usedPrepaymentAmount;
        this.weekdayDiscountRatio = weekdayDiscountRatio;
        this.weekdayDiscountAmount = weekdayDiscountAmount;
        this.levelDiscountRatio = levelDiscountRatio;
        this.levelDiscountAmount = levelDiscountAmount;
        this.usedVoucherAmount = usedVoucherAmount;
        this.finalPayment = finalPayment;
        this.level = user.getLevel();
        this.couponDiscountRatio = couponDiscountRatio;
        this.couponDiscountAmount = couponDiscountAmount;
    }
}
