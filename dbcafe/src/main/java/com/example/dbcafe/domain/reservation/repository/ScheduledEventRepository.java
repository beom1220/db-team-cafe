package com.example.dbcafe.domain.reservation.repository;

import com.example.dbcafe.domain.reservation.domain.ScheduledEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduledEventRepository extends JpaRepository<ScheduledEvent, Integer> {
}