package com.example.dbcafe.domain.reservation.service;

import com.example.dbcafe.domain.reservation.repository.ReservationBolckRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationBlockService {
    private final ReservationBolckRepository reservationBolckRepository;
}
