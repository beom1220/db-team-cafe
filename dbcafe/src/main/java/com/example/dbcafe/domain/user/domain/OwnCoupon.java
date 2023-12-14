package com.example.dbcafe.domain.user.domain;

import com.example.dbcafe.domain.order.domain.Orders;
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
public class OwnCoupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orders_id")
    private Orders orders;

    @Column(nullable = false, name = "created_at")
    @CreatedDate
    private Date createdAt;

    private Date dueDate;

    private CouponStatus couponStatus;

    private Date usedAt;

    private int discountPrice;

    public OwnCoupon(Coupon coupon, User user, CouponStatus couponStatus) {
        this.coupon = coupon;
        this.user = user;
        this.couponStatus = couponStatus;
    }

    public OwnCoupon(Coupon coupon, User user, Date dueDate, CouponStatus couponStatus) {
        this.coupon = coupon;
        this.user = user;
        this.dueDate = dueDate;
        this.couponStatus = couponStatus;
    }
}
