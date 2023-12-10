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
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        List<Event> events = eventRepository.findAllEvent();
        List<EventStatisticsDto> dtos = new ArrayList<>();
        for (Event event : events) {
            int soldOutCount = 0;
            List<ScheduledEvent> scheduledEvents = event.getScheduledEvents();
            for (ScheduledEvent se : scheduledEvents) {
                if (event.getCapacity() <= se.getEntrants().size()) {
                    soldOutCount++;
                }
            }
            double rating = (double) event.getRatingTotal() / event.getReviewCount();
            EventStatisticsDto dto = new EventStatisticsDto(event.getId(),
                    event.getTitle(), scheduledEvents.size(), soldOutCount, rating);

            dtos.add(dto);
        }
        return dtos;
    }
}
