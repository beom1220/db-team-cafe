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
public class LevelHistory { // 등급 이력
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private int coin;

    @Enumerated(EnumType.STRING)
    private Level level;

    private int year;

    private int month;

    public LevelHistory(User user, int coin, Level level, int year, int month) {
        this.user = user;
        this.coin = coin;
        this.level = level;
        this.year = year;
        this.month = month;
    }
}
