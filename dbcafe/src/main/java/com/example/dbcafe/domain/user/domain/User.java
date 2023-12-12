package com.example.dbcafe.domain.user.domain;

import com.example.dbcafe.domain.order.domain.Cart;
import com.example.dbcafe.domain.reservation.domain.Entrant;
import com.example.dbcafe.domain.reservation.domain.Reservation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class User {
    @Id
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "super_user_id")
    private User user;

    private String pw;

    private String name;

    private String phone;

    private int age;

    private boolean isMale;

    private int mileage;

    private int accumulation;

    private int coin;

    private int prizeChance;

    @Enumerated(EnumType.STRING)
    private Level level;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<OwnCoupon> ownCoupons;

    @OneToOne(mappedBy = "user", cascade = CascadeType.REMOVE)
    private Cart cart;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Reservation> reservations;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Entrant> entrants;

    public User(String id, User user, String pw, String name, String phone, int age, boolean isMale, int mileage, int accumulation, int coin, int prizeChance, Level level) {
        this.id = id;
        this.user = user;
        this.pw = pw;
        this.name = name;
        this.phone = phone;
        this.age = age;
        this.isMale = isMale;
        this.mileage = mileage;
        this.accumulation = accumulation;
        this.coin = coin;
        this.prizeChance = prizeChance;
        this.level = level;
    }
}
