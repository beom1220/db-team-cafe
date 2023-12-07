package com.example.dbcafe.domain.reservation.service;

import com.example.dbcafe.domain.reservation.domain.ScheduledEvent;
import com.example.dbcafe.domain.reservation.repository.ScheduledEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduledEventService {
    private final ScheduledEventRepository scheduledEventRepository;

    public List<ScheduledEvent> findAllRecruiting() {
        return scheduledEventRepository.findAllByIsClosed(false);
    }
}
