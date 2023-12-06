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
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

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
}
