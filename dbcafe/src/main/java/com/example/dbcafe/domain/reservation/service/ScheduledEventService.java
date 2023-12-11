package com.example.dbcafe.domain.reservation.service;

import com.example.dbcafe.domain.reservation.domain.*;
import com.example.dbcafe.domain.reservation.dto.*;
import com.example.dbcafe.domain.reservation.repository.EventRepository;
import com.example.dbcafe.domain.reservation.repository.PlaceRepository;
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
    private final EventRepository eventRepository;
    private final ReservationBlockService reservationBlockService;
    private final PlaceRepository placeRepository;

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

    public List<EntrantListDto> findAllEntrant(ScheduledEvent scheduledEvent) {
        List<Entrant> entrants = entrantService.findAllEntrantByScheduledEvent(scheduledEvent);
        List<EntrantListDto> dtos = new ArrayList<>();
        for (Entrant entrant : entrants) {
            String gender = "남";
            if (!entrant.isMale()) {
                gender = "여";
            }
            EntrantListDto dto = new EntrantListDto(entrant.getId(), entrant.getName(),
                    entrant.getPhone(), gender, entrant.getAge(), entrant.getApplicationStatus());
            dtos.add(dto);
        }
        return dtos;
    }

    public void submitEntrant(int entrantId) {
        entrantService.submitEntrant(entrantId);
    }

    public void rejectEntrant(int entrantId, String rejectionReason) {
        entrantService.rejectEntrant(entrantId, rejectionReason);
    }

    public List<EventDto> findAllEvent() {
        List<Event> events = eventRepository.findAllEvent();
        List<EventDto> dtos = new ArrayList<>();
        for (Event event : events) {
            EventDto dto = new EventDto(event.getId(), event.getTitle());
            dtos.add(dto);
        }
        return dtos;
    }

    public List<ReservationBlockDto> findAllBookableBlock() {
        return reservationBlockService.findAllBookableBlock();
    }

    public void addScheduledEvent(ScheduledDto dto) {
        Event event = eventRepository.findEventById(dto.getEventId());
        int placeId = reservationBlockService.findPlaceByDateAndTime(dto.getDate(), dto.getStartTime());
        Place place = placeRepository.findPlaceById(placeId);
        ScheduledEvent scheduledEvent = new ScheduledEvent(event, place,
                dto.getDate(), dto.getStartTime(), dto.getEndTime(),
                false, dto.getTag());
        scheduledEventRepository.save(scheduledEvent);
        ReservationBlock block = reservationBlockService.findBlockByPlaceAndDateAndStartTime(place, dto.getDate(), dto.getStartTime());
        block.setBookable(false);
        reservationBlockService.save(block);
    }
}
