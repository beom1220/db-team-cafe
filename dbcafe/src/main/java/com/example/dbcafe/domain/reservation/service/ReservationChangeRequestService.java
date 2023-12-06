package com.example.dbcafe.domain.reservation.service;

import com.example.dbcafe.domain.reservation.repository.ReservationChangeRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationChangeRequestService {
    private final ReservationChangeRequestRepository reservationChangeRequestRepository;
}
