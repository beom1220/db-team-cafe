package com.example.dbcafe.domain.reservation.service;

import com.example.dbcafe.domain.reservation.domain.Entrant;
import com.example.dbcafe.domain.reservation.domain.Event;
import com.example.dbcafe.domain.reservation.domain.ScheduledEvent;
import com.example.dbcafe.domain.reservation.dto.EventReviewDto;
import com.example.dbcafe.domain.reservation.dto.ScheduledEventDetailDto;
import com.example.dbcafe.domain.reservation.dto.ScheduledEventListDto;
import com.example.dbcafe.domain.reservation.repository.ScheduledEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduledEventService {
    private final ScheduledEventRepository scheduledEventRepository;
    private final EntrantService entrantService;

    public List<ScheduledEvent> findAllRecruiting() {
        return scheduledEventRepository.findAllByIsClosed(false);
    }

    public ScheduledEvent findById(int id) {
        return scheduledEventRepository.findScheduledEventById(id);
    }

    public List<ScheduledEventListDto> convertToListDto(List<ScheduledEvent> items) {
        List<ScheduledEventListDto> dtos = new ArrayList<>();
        for (ScheduledEvent item : items) {
            Event event = item.getEvent();
            int volunteer = item.getEntrants().size();
            double rating = (double) event.getRatingTotal() / event.getReviewCount();
            ScheduledEventListDto dto = new ScheduledEventListDto(item.getId(),
                    event.getTitle(), rating, item.getDate(),
                    item.getStartTime(), item.getEndTime(),
                    event.getCapacity(), volunteer, event.getImg(), item.getTag());
            dtos.add(dto);
        }
        return dtos;
    }

    public ScheduledEventDetailDto convertToDetailDto(ScheduledEvent se) {
        Event event = se.getEvent();
        List<ScheduledEvent> scheduledEvents = event.getScheduledEvents();
        int totalAttendedUser = 0;
        int totalReviewedUser = 0;
        for (ScheduledEvent item : scheduledEvents) {
            totalAttendedUser += entrantService.countAttendedUser(item);
            totalReviewedUser += entrantService.countReviewedUser(item);
        }
        double rating = (double) event.getRatingTotal() / event.getReviewCount();
        int volunteer = se.getEntrants().size();

        ScheduledEventDetailDto dto = new ScheduledEventDetailDto(se.getId(),
                event.getTitle(), event.getFee(), totalAttendedUser, totalReviewedUser,
                rating, se.getDate(), se.getStartTime(), se.getEndTime(),
                event.getCapacity(), volunteer, event.getImg(), se.getTag());

        return dto;
    }

    public List<EventReviewDto> findReviewsByScheduledEvent(ScheduledEvent scheduledEvent) {
        Event event = scheduledEvent.getEvent();
        List<ScheduledEvent> scheduledEvents = event.getScheduledEvents();
        List<Entrant> entrants = entrantService.findFiveByEvent(scheduledEvents);
        List<EventReviewDto> dtos = entrantService.convertToReviewDto(entrants);
        return dtos;
    }
}
