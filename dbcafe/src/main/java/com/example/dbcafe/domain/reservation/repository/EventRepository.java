package com.example.dbcafe.domain.reservation.repository;

import com.example.dbcafe.domain.reservation.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Integer> {
    Event findEventById(int eventId);

    List<Event> findAllEvent();
}