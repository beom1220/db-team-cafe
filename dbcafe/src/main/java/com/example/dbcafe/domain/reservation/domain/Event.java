package com.example.dbcafe.domain.reservation.domain;

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
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    private String content;

    private boolean isRepOnly;

    private int capacity;

    private int fee;

    private String img;

    private int reviewCount;

    private double ratingTotal;

    @OneToMany(mappedBy = "event", cascade = CascadeType.REMOVE)
    private List<ScheduledEvent> scheduledEvents;
}
