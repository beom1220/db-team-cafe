package com.example.dbcafe.domain.reservation.repository;

import com.example.dbcafe.domain.reservation.domain.ScheduledEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduledEventRepository extends JpaRepository<ScheduledEvent, Integer> {
    List<ScheduledEvent> findAllByIsClosed(boolean b);

    ScheduledEvent findScheduledEventById(int id);
}