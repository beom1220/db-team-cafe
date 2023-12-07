package com.example.dbcafe.domain.reservation.service;

import com.example.dbcafe.domain.reservation.domain.ReservationBolck;
import com.example.dbcafe.domain.reservation.dto.DayOfReservationBlockDto;
import com.example.dbcafe.domain.reservation.repository.ReservationBolckRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationBlockService {
    private final ReservationBolckRepository reservationBolckRepository;

    public List<DayOfReservationBlockDto> showBasicDays() {
        LocalDate today = LocalDate.now();

        List<ReservationBolck> days = reservationBolckRepository.findByDateGreaterThanEqual(today);

        for (ReservationBolck day : days) {
            day.getDate()
        }
    }
}
