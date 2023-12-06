package com.example.dbcafe.domain.reservation.service;

import com.example.dbcafe.domain.reservation.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
}
