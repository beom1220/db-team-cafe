package com.example.dbcafe.domain.reservation.domain;

import com.example.dbcafe.domain.order.domain.Menu;
import com.example.dbcafe.domain.order.domain.PaymentMethod;
import com.example.dbcafe.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Entrant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "scheduled_event_id")
    private ScheduledEvent scheduledEvent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    private Menu menu;

    private String name;

    private String phone;

    private int age;

    private boolean isMale;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus applicationStatus;

    private boolean isRefunded;

    private boolean isAttended;

    private String rejectionReason;

    private String review;

    private double rating;

    private Date reviewedDate;

    @Column(nullable = false, name = "created_at")
    @CreatedDate
    private Date createdAt;
}
