package com.example.dbcafe.domain.reservation.service;

import com.example.dbcafe.domain.reservation.domain.DayOfWeekInKorean;
import com.example.dbcafe.domain.reservation.domain.Place;
import com.example.dbcafe.domain.reservation.domain.ReservationBlock;
import com.example.dbcafe.domain.reservation.dto.DayOfReservationBlockDto;
import com.example.dbcafe.domain.reservation.dto.PackageReservationBlockDto;
import com.example.dbcafe.domain.reservation.dto.ReservationBlockDto;
import com.example.dbcafe.domain.reservation.dto.TimeOfReservationBlockDto;
import com.example.dbcafe.domain.reservation.repository.ReservationBlockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ReservationBlockService {
    private final ReservationBlockRepository reservationBlockRepository;

    public List<DayOfReservationBlockDto> showBasicDays() {
        LocalDate today = LocalDate.now();

        List<ReservationBlock> blocks = reservationBlockRepository.findByDateGreaterThanEqual(today);

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
                if (block.getIsBookable()) {
                    isBookable = true;
                    break;
                }
            }
            DayOfReservationBlockDto dto = new DayOfReservationBlockDto(entry.getKey(), isBookable, DayOfWeekInKorean
                    .valueOf(entry.getKey().getDayOfWeek().name()).getDay());
            dtos.add(dto);

        }
        return dtos;
    }

    public List<TimeOfReservationBlockDto> showTimeBlocks(LocalDate date) {
        List<ReservationBlock> blocksOnDate = reservationBlockRepository.findByDate(date);

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
                if (block.getIsBookable()) {
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
        List<ReservationBlock> blocks = reservationBlockRepository.findByDateGreaterThanEqualAndIsBookableTrueOrderByDateAscStartTimeAsc(LocalDate.now());
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
        List<ReservationBlock> blocks = reservationBlockRepository.findAllReservationBlockByDateAndStartTimeAndIsBookableFalse(date, startTime);
        return blocks.size() + 1;
    }

    public ReservationBlock findBlockByPlaceAndDateAndStartTime(Place place, LocalDate date, LocalTime startTime) {
        return reservationBlockRepository.findReservationBlockByPlaceAndDateAndStartTime(place, date, startTime);
    }

    public ReservationBlock save(ReservationBlock block) {
        return reservationBlockRepository.save(block);
    }

    public List<PackageReservationBlockDto> findAllPackagesAndConvertToDto() {
        LocalDate today = LocalDate.now();
        List<ReservationBlock> blocks = reservationBlockRepository.findAllByIsBookableAndDateGreaterThanEqualOrderByDateAscStartTimeAsc(true, today);
        return getPackageReservationBlockDtos(blocks);
    }

    private static List<PackageReservationBlockDto> getPackageReservationBlockDtos(List<ReservationBlock> blocks) {
        List<PackageReservationBlockDto> dtos = new ArrayList<>();
        for (ReservationBlock b : blocks) {
            ReservationBlock plusOneWeek = b;
            ReservationBlock plusTwoWeek = b;
            ReservationBlock plusThreeWeek = b;
            plusOneWeek.setDate(b.getDate().plusDays(7));
            plusTwoWeek.setDate(b.getDate().plusDays(14));
            plusThreeWeek.setDate(b.getDate().plusDays(21));
            if (!blocks.contains(plusOneWeek) | !blocks.contains(plusTwoWeek) | !blocks.contains(plusThreeWeek)) {
                continue;
            } else {
                String dayOfWeek = DayOfWeekInKorean.valueOf(b.getDate().getDayOfWeek().name()).getDay();
                dtos.add(new PackageReservationBlockDto(b.getDate(), b.getStartTime(),
                        b.getEndTime(), dayOfWeek));
            }
        }
        return dtos;
    }

    public List<PackageReservationBlockDto> searchPackagesAndConvertToDto(String dayOfWeek, String startTime) {
        LocalDate today = LocalDate.now();
        boolean timeEmpty = true;
        boolean dayEmpty = true;
        LocalTime time = null;
        DayOfWeek dow = null;
        if (!startTime.isEmpty()) {
            time = LocalTime.parse(startTime);
            timeEmpty = false;
        }
        if (!dayOfWeek.isEmpty()) {
            dow = convertDayOfWeek(dayOfWeek);
            dayEmpty = false;
        }
        List<ReservationBlock> blocks = new ArrayList<>();
        if (!timeEmpty && !dayEmpty) {
            blocks = reservationBlockRepository.findAllByIsBookableAndDayOfWeekAndStartTimeAndDateGreaterThanEqualOrderByDateAsc(true, dow, time, today);
        } else if (!timeEmpty && dayEmpty) {
            blocks = reservationBlockRepository.findAllByIsBookableAndStartTimeAndDateGreaterThanEqualOrderByDateAsc(true, time, today);
        } else if (timeEmpty && !dayEmpty) {
            blocks = reservationBlockRepository.findAllByIsBookableAndDayOfWeekAndDateGreaterThanEqualOrderByDateAscStartTimeAsc(true, dow, today);
        } else {
            blocks = reservationBlockRepository.findAllByIsBookableAndDateGreaterThanEqualOrderByDateAscStartTimeAsc(true, today);
        }
        return getPackageReservationBlockDtos(blocks);
    }

    public DayOfWeek convertDayOfWeek(String dayOfWeek) {
        switch (dayOfWeek) {
            case "월요일":
                return DayOfWeek.MONDAY;
            case "화요일":
                return DayOfWeek.TUESDAY;
            case "수요일":
                return DayOfWeek.WEDNESDAY;
            case "목요일":
                return DayOfWeek.THURSDAY;
            case "금요일":
                return DayOfWeek.FRIDAY;
            case "토요일":
                return DayOfWeek.SATURDAY;
            case "일요일":
                return DayOfWeek.SUNDAY;
            default:
                return null;
        }
    }
}
