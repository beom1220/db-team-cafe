package com.example.dbcafe.domain.reservation.service;

import com.example.dbcafe.domain.reservation.repository.ScheduledEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduledEventService {
    private final ScheduledEventRepository scheduledEventRepository;
}
