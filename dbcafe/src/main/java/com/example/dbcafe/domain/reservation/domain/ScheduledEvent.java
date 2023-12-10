package com.example.dbcafe.domain.reservation.domain;

import com.example.dbcafe.domain.user.domain.OwnCoupon;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.swing.text.html.HTML;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class ScheduledEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
    private Place place;

    private LocalDate date;

    private LocalTime startTime;

    private LocalTime endTime;

    private boolean isClosed;

    @Enumerated(EnumType.STRING)
    private Tag tag;

    @OneToMany(mappedBy = "scheduledEvent", cascade = CascadeType.REMOVE)
    private List<Entrant> entrants;

    public ScheduledEvent(Event event, Place place, LocalDate date, LocalTime startTime, LocalTime endTime, boolean isClosed, Tag tag) {
        this.event = event;
        this.place = place;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isClosed = isClosed;
        this.tag = tag;
    }
}
