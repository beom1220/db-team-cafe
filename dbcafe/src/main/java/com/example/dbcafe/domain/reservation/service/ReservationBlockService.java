package com.example.dbcafe.domain.reservation.service;

import com.example.dbcafe.domain.reservation.domain.ReservationBlock;
import com.example.dbcafe.domain.reservation.dto.DayOfReservationBlockDto;
import com.example.dbcafe.domain.reservation.dto.TimeOfReservationBlockDto;
import com.example.dbcafe.domain.reservation.repository.ReservationBolckRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ReservationBlockService {
    private final ReservationBolckRepository reservationBolckRepository;

    public List<DayOfReservationBlockDto> showBasicDays() {
        LocalDate today = LocalDate.now();

        List<ReservationBlock> blocks = reservationBolckRepository.findByDateGreaterThanEqual(today);

        Map<LocalDate, List<ReservationBlock>> days = new HashMap<>();
        for (ReservationBlock block : blocks) {
            LocalDate date = block.getDate();
            if (!days.containsKey(date)) {
                days.put(date, new ArrayList<>());
            }
            days.get(date).add(block);
        }

        List<DayOfReservationBlockDto> dtos = new ArrayList<>();
        for (Map.Entry<LocalDate, List<ReservationBlock>> entry : days.entrySet()) {
            boolean isBookable = false;
            for (ReservationBlock block : entry.getValue()) {
                if (block.isBookable()) {
                    isBookable = true;
                    break;
                }
            }
            DayOfReservationBlockDto dto = new DayOfReservationBlockDto(entry.getKey(), isBookable);
            dtos.add(dto);

        }
        return dtos;
    }

    public List<TimeOfReservationBlockDto> showTimeBlocks(LocalDate date) {
        List<ReservationBlock> blocksOnDate = reservationBolckRepository.findByDate(date);

        Map<LocalTime, List<ReservationBlock>> groupedBlocks = new HashMap<>();
        for (ReservationBlock block : blocksOnDate) {
            LocalTime startTime = block.getStartTime();
            if (!groupedBlocks.containsKey(startTime)) {
                groupedBlocks.put(startTime, new ArrayList<>());
            }
            groupedBlocks.get(startTime).add(block);
        }

        List<TimeOfReservationBlockDto> dtos = new ArrayList<>();
        for (Map.Entry<LocalTime, List<ReservationBlock>> entry : groupedBlocks.entrySet()) {
            LocalTime startTime = entry.getKey();
            LocalTime endTime = entry.getValue().get(0).getEndTime();
            boolean isBookable = false;
            for (ReservationBlock block : entry.getValue()) {
                if (block.isBookable()) {
                    isBookable = true;
                    break;
                }
            }
            TimeOfReservationBlockDto dto = new TimeOfReservationBlockDto(startTime, endTime, isBookable);
            dtos.add(dto);
        }
        return dtos;
    }
}
