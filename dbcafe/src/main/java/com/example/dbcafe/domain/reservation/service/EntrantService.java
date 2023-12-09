package com.example.dbcafe.domain.reservation.service;

import com.example.dbcafe.domain.reservation.domain.Entrant;
import com.example.dbcafe.domain.reservation.domain.Event;
import com.example.dbcafe.domain.reservation.domain.ScheduledEvent;
import com.example.dbcafe.domain.reservation.dto.EventReviewDto;
import com.example.dbcafe.domain.reservation.dto.WriteReviewDto;
import com.example.dbcafe.domain.reservation.repository.EntrantRepository;
import com.example.dbcafe.domain.reservation.repository.EventRepository;
import com.example.dbcafe.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EntrantService {
    private final EntrantRepository entrantRepository;
    private final EventRepository eventRepository;

    public int countAttendedUser(ScheduledEvent scheduledEvent) {
        return entrantRepository.findAllEntrantByScheduledEventAndIsAttended(scheduledEvent, true).size();
    }

    public int countReviewedUser(ScheduledEvent item) {
        return entrantRepository.findAllEntrantByScheduledEventAndReviewIsNotNullAndRatingIsNotNull(item).size();
    }

    public List<Entrant> findFiveByEvent(List<ScheduledEvent> scheduledEvents) {
        return entrantRepository.findTop5ByScheduledEventInAndReviewIsNotNullOrderByReviewedDateDesc(scheduledEvents);
    }

    public List<EventReviewDto> convertToReviewDto(List<Entrant> entrants) {
        List<EventReviewDto> dtos = new ArrayList<>();
        for (Entrant entrant : entrants) {
            LocalDate localDate = entrant.getReviewedDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            EventReviewDto dto = new EventReviewDto(entrant.getUser().getId(),
                    entrant.getReview(), entrant.getRating(), localDate);
            dtos.add(dto);
        }
        return dtos;
    }

    public List<Entrant> findAllReviewByEvent(Event event) {
        return entrantRepository.findAllEntrantByScheduledEventInAndReviewIsNotNullAndRatingIsNotNull(event.getScheduledEvents());
    }

    public List<Entrant> findEntrantsForCheckReviewable(User user, Event event) {
        return entrantRepository.findAllEntrantByScheduledEventInAndIsAttendedAndReviewIsNullOrderByReviewedDateDesc(event.getScheduledEvents(), true);
    }

    public Entrant findEntrantForWriteReview(Event event, User user) {
        // 해당 이벤트에서 개최된 개최이벤트에 참여한 참여 기록 중 Review가 Null인 가장 최근의 Entrant를 가져오고,
        // 나머지 Entrant의 Review에는 "리뷰작성불가" 저장하여 조회 안 되도록 함. (Review와 Rating이 모두 null이고, isAttended가 true여야 리뷰 작성 가능한 것으로)
        List<Entrant> entrants = entrantRepository.findAllEntrantByScheduledEventInAndIsAttendedAndReviewIsNullOrderByReviewedDateDesc(event.getScheduledEvents(), true);
        boolean isFirst = true;
        Entrant reviewEntrant = null;
        for (Entrant entrant : entrants) {
            if (isFirst) {
                isFirst = false;
                reviewEntrant = entrant;
            } else {
                entrant.setReview("리뷰작성불가");
                entrantRepository.save(entrant);
            }
        }
        return reviewEntrant;
    }

    public void addReview(Entrant entrant, WriteReviewDto dto) {
        entrant.setReview(dto.getReview());
        entrant.setRating(dto.getRating());
        entrant.setReviewedDate(LocalDateTime.now());

        entrantRepository.save(entrant);

        Event event = entrant.getScheduledEvent().getEvent();
        event.setReviewCount(event.getReviewCount() + 1);
        event.setRatingTotal(event.getRatingTotal() + dto.getRating());
        eventRepository.save(event);
    }
}
