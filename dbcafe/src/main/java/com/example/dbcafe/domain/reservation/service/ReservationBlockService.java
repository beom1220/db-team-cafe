package com.example.dbcafe.domain.reservation.service;

import com.example.dbcafe.domain.reservation.domain.DayOfWeekInKorean;
import com.example.dbcafe.domain.reservation.domain.Place;
import com.example.dbcafe.domain.reservation.domain.ReservationBlock;
import com.example.dbcafe.domain.reservation.dto.*;
import com.example.dbcafe.domain.reservation.repository.ReservationBlockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
        List<ReservationBlock> blocksOnDate = reservationBlockRepository.findByDate(date); // 해당 날짜 블럭 일단 다 가져옴.

        Map<LocalTime, List<ReservationBlock>> groupedBlocks = new HashMap<>(); // 시간대별로 끊었음. <날짜-시간대> 같은 거 묶인 거임.
        for (ReservationBlock block : blocksOnDate) {
            LocalTime startTime = block.getStartTime();
            if (!groupedBlocks.containsKey(startTime)) {
                groupedBlocks.put(startTime, new ArrayList<>());
            }
            groupedBlocks.get(startTime).add(block);
        }

        List<TimeOfReservationBlockDto> dtos = new ArrayList<>(); // Dto로 변환과정
        for (Map.Entry<LocalTime, List<ReservationBlock>> entry : groupedBlocks.entrySet()) {
            LocalTime startTime = entry.getKey();
            LocalTime endTime = entry.getValue().get(0).getEndTime();
            boolean isBookable = false;
            ReservationBlock b = null;
            int blockId = 0;
            for (ReservationBlock block : entry.getValue()) {
                if (block.getIsBookable()) {
                    isBookable = true;
                    b = reservationBlockRepository.findFirstByDateAndStartTimeAndIsBookableOrderByPlaceIdAsc(date, startTime, true);
                    blockId = b.getId();
                    break;
                }
            }

            TimeOfReservationBlockDto dto = new TimeOfReservationBlockDto(blockId, startTime, endTime, isBookable);
            dtos.add(dto);
        }
        return dtos;
    }

    public List<ReservationBlockDateTimeDto> findAllBookableBlock() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
//        List<ReservationBlock> blocks = reservationBlockRepository.findByDateGreaterThanEqualAndIsBookableTrueOrderByDateAscStartTimeAsc(LocalDate.now());
        List<ReservationBlock> blocks = reservationBlockRepository.findDistinctByIsBookableAndDateGreaterThanEqualOrderByDateAscStartTimeAsc(LocalDate.now());
        List<ReservationBlockDto> dtos = new ArrayList<>();
        for (ReservationBlock block : blocks) {
            ReservationBlockDto dto = new ReservationBlockDto(block.getDate(),
                    block.getStartTime(), block.getEndTime());
            dtos.add(dto);
//            if (!dtos.contains(dto)) { // 같은 날짜, 같은 시간대는 하나만 띄우기 위함.
//                dtos.add(dto);
//            }
        }
        List<ReservationBlockDateTimeDto> dateTimeDtos = new ArrayList<>();
        for (ReservationBlockDto dto : dtos) {
            ReservationBlock block = reservationBlockRepository.findFirstByDateAndStartTimeAndIsBookableOrderByPlaceIdAsc(dto.getDate(), dto.getStartTime(), true);
            String dateString = block.getDate().format(dateFormatter);
            String startTimeString = block.getStartTime().format(timeFormatter);
            String endTimeString = block.getEndTime().format(timeFormatter);
            String dateTime = dateString + " " + startTimeString + "~" + endTimeString;
            dateTimeDtos.add(new ReservationBlockDateTimeDto(block.getId(), dateTime));
        }
        return dateTimeDtos;
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
//        List<ReservationBlock> blocks = reservationBlockRepository.findAllByIsBookableAndDateGreaterThanEqualOrderByDateAscStartTimeAsc(true, today); // 오늘 이후의 모든 예약 가능한 블록을 가져옴. (날짜, 시간 순으로 정렬)
        List<ReservationBlock> blocks = reservationBlockRepository.findDistinctByIsBookableAndDateGreaterThanEqualOrderByDateAscStartTimeAsc(today); // 오늘 이후 예약 가능한 블럭이 있는 날짜와 시간에 대해 하나씩 블럭을 가져옴.
        return getPackageReservationBlockDtos(blocks);
    }

    private List<PackageReservationBlockDto> getPackageReservationBlockDtos(List<ReservationBlock> blocks) {
        List<PackageReservationBlockDto> dtos = new ArrayList<>(); // 담을 List 만듦.
        outer: for (ReservationBlock b : blocks) { // 오늘 이후 가능한 모든 블럭 탐색
            LocalDate startDate = b.getDate();
            LocalTime startTime = b.getStartTime();
            for (int i = 7; i <= 21; i += 7) {
                ReservationBlock checkBlock = reservationBlockRepository.findFirstByDateAndStartTimeAndIsBookableOrderByPlaceIdAsc(startDate.plusDays(i), startTime, true);
                if (checkBlock == null) {
                    continue outer;
                }
            }
            String dayOfWeek = DayOfWeekInKorean.valueOf(b.getDate().getDayOfWeek().name()).getDay();
            dtos.add(new PackageReservationBlockDto(b.getId(), b.getDate(), b.getStartTime(), b.getEndTime(), dayOfWeek));
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
//            blocks = reservationBlockRepository.findAllByIsBookableAndDayOfWeekAndStartTimeAndDateGreaterThanEqualOrderByDateAsc(true, dow, time, today);
            blocks = reservationBlockRepository.findDistinctByIsBookableAndDayOfWeekAndStartTimeAndDateGreaterThanEqualOrderByDateAsc(dow, time, today);
        } else if (!timeEmpty && dayEmpty) {
//            blocks = reservationBlockRepository.findAllByIsBookableAndStartTimeAndDateGreaterThanEqualOrderByDateAsc(true, time, today);
            blocks = reservationBlockRepository.findDistinctByIsBookableAndStartTimeAndDateGreaterThanEqualOrderByDateAsc(time, today);
        } else if (timeEmpty && !dayEmpty) {
//            blocks = reservationBlockRepository.findAllByIsBookableAndDayOfWeekAndDateGreaterThanEqualOrderByDateAscStartTimeAsc(true, dow, today);
            blocks = reservationBlockRepository.findDistinctByIsBookableAndDayOfWeekAndDateGreaterThanEqualOrderByDateAscStartTimeAsc(dow, today);
        } else {
//            blocks = reservationBlockRepository.findAllByIsBookableAndDateGreaterThanEqualOrderByDateAscStartTimeAsc(true, today);
            blocks = reservationBlockRepository.findDistinctByIsBookableAndDateGreaterThanEqualOrderByDateAscStartTimeAsc(today);
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
