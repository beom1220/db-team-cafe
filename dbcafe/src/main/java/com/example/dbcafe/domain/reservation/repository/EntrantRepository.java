package com.example.dbcafe.domain.reservation.repository;

import com.example.dbcafe.domain.reservation.domain.Entrant;
import com.example.dbcafe.domain.reservation.domain.ScheduledEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EntrantRepository extends JpaRepository<Entrant, Integer> {
    List<Entrant> findAllEntrantByScheduledEventAndIsAttended(ScheduledEvent scheduledEvent, boolean b);

    List<Entrant> findAllEntrantByScheduledEventAndReviewIsNotNullAndRatingIsNotNull(ScheduledEvent item);

    List<Entrant> findTop5ByScheduledEventInAndReviewIsNotNullOrderByReviewedDateDesc(List<ScheduledEvent> scheduledEvents);

    List<Entrant> findAllEntrantByScheduledEventInAndIsAttendedAndReviewIsNullOrderByReviewedDateDesc(List<ScheduledEvent> scheduledEvents, boolean b);

    List<Entrant> findAllEntrantByScheduledEventInAndReviewIsNotNullAndRatingIsNotNull(List<ScheduledEvent> scheduledEvents);
}