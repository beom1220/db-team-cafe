package com.example.dbcafe.domain.reservation.service;

import com.example.dbcafe.domain.reservation.domain.Place;
import com.example.dbcafe.domain.reservation.domain.ReservationBlock;
import com.example.dbcafe.domain.reservation.dto.DayOfReservationBlockDto;
import com.example.dbcafe.domain.reservation.dto.ReservationBlockDto;
import com.example.dbcafe.domain.reservation.dto.TimeOfReservationBlockDto;
import com.example.dbcafe.domain.reservation.repository.ReservationBlockRepository;
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
    private final ReservationBlockRepository reservationBolckRepository;

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

    public List<ReservationBlockDto> findAllBookableBlock() {
        List<ReservationBlock> blocks = reservationBolckRepository.findByDateGreaterThanEqualAndIsBookableTrueOrderByDateAscStartTimeAsc(LocalDate.now());
        List<ReservationBlockDto> dtos = new ArrayList<>();
        for (ReservationBlock block : blocks) {
            ReservationBlockDto dto = new ReservationBlockDto(block.getDate(),
                    block.getStartTime(), block.getEndTime());

            if (!dtos.contains(dto)) { // 같은 날짜, 같은 시간대는 하나만 띄우기 위함.
                dtos.add(dto);
            }
        }
        return dtos;
    }

    public int findPlaceByDateAndTime(LocalDate date, LocalTime startTime) {
        List<ReservationBlock> blocks = reservationBolckRepository.findAllReservationBlockByDateAndStartTimeAndIsBookableFalse(date, startTime);
        return blocks.size();
    }

    public ReservationBlock findBlockByPlaceAndDateAndStartTime(Place place, LocalDate date, LocalTime startTime) {
        return reservationBolckRepository.findReservationBlockByPlaceAndDateAndStartTime(place, date, startTime);
    }

    public ReservationBlock save(ReservationBlock block) {
        return reservationBolckRepository.save(block);
    }
}
