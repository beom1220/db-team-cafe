package com.example.dbcafe.domain.user.domain;

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
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private int discountRatio;

    private int maxDiscount;

    private int period; // 유효기간 (일 단위로)

    private int issuance;

    private int maxIssuance;

    public Coupon(String name, int discountRatio, int maxDiscount, int period, int maxIssuance) {
        this.name = name;
        this.discountRatio = discountRatio;
        this.maxDiscount = maxDiscount;
        this.period = period;
        this.maxIssuance = maxIssuance;
    }
}
