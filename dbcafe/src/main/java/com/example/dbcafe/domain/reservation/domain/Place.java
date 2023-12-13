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
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int capacity;

    private boolean hasHvac;

    private boolean hasVideoDevice;

    private int plugCount;

    private boolean hasWifi;

    public Place(int capacity, boolean hasHvac, boolean hasVideoDevice, int plugCount, boolean hasWifi) {
        this.capacity = capacity;
        this.hasHvac = hasHvac;
        this.hasVideoDevice = hasVideoDevice;
        this.plugCount = plugCount;
        this.hasWifi = hasWifi;
    }
}
