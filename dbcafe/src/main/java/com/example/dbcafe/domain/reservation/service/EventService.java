package com.example.dbcafe.domain.reservation.service;

import com.example.dbcafe.domain.reservation.domain.Entrant;
import com.example.dbcafe.domain.reservation.domain.Event;
import com.example.dbcafe.domain.reservation.domain.ScheduledEvent;
import com.example.dbcafe.domain.reservation.dto.EventReviewDto;
import com.example.dbcafe.domain.reservation.dto.EventStatisticsDto;
import com.example.dbcafe.domain.reservation.dto.WriteReviewDto;
import com.example.dbcafe.domain.reservation.repository.EventRepository;
import com.example.dbcafe.domain.user.domain.User;
import com.example.dbcafe.domain.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final EntrantService entrantService;
    private final UserService userService;

    public List<EventReviewDto> findReviewsByEventId(int eventId) {
        Event event = eventRepository.findEventById(eventId);
        List<Entrant> entrants = entrantService.findAllReviewByEvent(event);
        List<EventReviewDto> dtos = entrantService.convertToReviewDto(entrants);

        return dtos;
    }

    public void addReview(int eventId, WriteReviewDto dto, HttpSession session) {
        Event event = eventRepository.findEventById(eventId);
        User user = userService.findById((String) session.getAttribute("loggedInUser"));
        Entrant entrant = entrantService.findEntrantForWriteReview(event, user);
        entrantService.addReview(entrant, dto);
    }

    public boolean checkReviewable(HttpSession session, int eventId) {
        User user = userService.findById((String) session.getAttribute("loggedInUser"));
        Event event = eventRepository.findEventById(eventId);
        List<Entrant> entrants = entrantService.findEntrantsForCheckReviewable(user, event);
        return !entrants.isEmpty();
    }

    public List<EventStatisticsDto> findStatistics() {
        List<Event> events = eventRepository.findAll();
        List<EventStatisticsDto> dtos = new ArrayList<>();
        for (Event event : events) {
            int soldOutCount = 0;
            List<ScheduledEvent> scheduledEvents = event.getScheduledEvents();
            log.info("---------------------------");
            log.info("이벤트 이름 : " + event.getTitle());
            log.info("개최이벤트 수 : " + scheduledEvents.size());
            log.info("수용인원 수 : " + event.getCapacity());
            for (ScheduledEvent se : scheduledEvents) {
                log.info("참가자 수 : " + se.getEntrants().size());
                if (event.getCapacity() <= se.getEntrants().size()) {
                    soldOutCount++;
                }
            }
            int reviewCount = 1;
            if (event.getReviewCount() != 0) {
                reviewCount = event.getReviewCount();
            }
            double rating = (double) event.getRatingTotal() / reviewCount;
            EventStatisticsDto dto = new EventStatisticsDto(event.getId(),
                    event.getTitle(), scheduledEvents.size(), soldOutCount, rating);

            dtos.add(dto);
        }
        return dtos;
    }

    public Event findEventById(int eventId) {
        return eventRepository.findEventById(eventId);
    }
}
