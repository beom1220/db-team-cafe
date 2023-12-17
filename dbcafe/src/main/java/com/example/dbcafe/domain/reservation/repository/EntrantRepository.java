package com.example.dbcafe.domain.reservation.repository;

import com.example.dbcafe.domain.reservation.domain.Entrant;
import com.example.dbcafe.domain.reservation.domain.ScheduledEvent;
import com.example.dbcafe.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EntrantRepository extends JpaRepository<Entrant, Integer> {
    List<Entrant> findAllEntrantByScheduledEventAndIsAttended(ScheduledEvent scheduledEvent, boolean b);

    List<Entrant> findAllEntrantByScheduledEventAndReviewIsNotNullAndRatingIsNotNull(ScheduledEvent item);

    List<Entrant> findTop5ByScheduledEventInAndReviewIsNotNullOrderByRatingDesc(List<ScheduledEvent> scheduledEvents);

    List<Entrant> findAllEntrantByScheduledEventInAndIsAttendedAndReviewIsNullOrderByReviewedDateDesc(List<ScheduledEvent> scheduledEvents, boolean b);

    List<Entrant> findAllEntrantByScheduledEventInAndReviewIsNotNullAndRatingIsNotNull(List<ScheduledEvent> scheduledEvents);

    List<Entrant> findAllEntrantByScheduledEvent(ScheduledEvent scheduledEvent);

    Entrant findEntrantById(int entrantId);

    List<Entrant> findAllEntrantByScheduledEventInAndUserAndIsAttendedAndReviewIsNullOrderByReviewedDateDesc(List<ScheduledEvent> scheduledEvents, User user, boolean b);

    List<Entrant> findAllEntrantByScheduledEventInAndReviewIsNotNullOrderByReviewedDateDesc(List<ScheduledEvent> scheduledEvents);

    Entrant findEntrantByScheduledEventIdAndUserId(int i, String s);
}