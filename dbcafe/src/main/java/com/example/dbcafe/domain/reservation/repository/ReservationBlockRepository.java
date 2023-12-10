package com.example.dbcafe.domain.reservation.repository;

import com.example.dbcafe.domain.reservation.domain.Place;
import com.example.dbcafe.domain.reservation.domain.ReservationBlock;
import com.example.dbcafe.domain.reservation.dto.ReservationBlockDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ReservationBlockRepository extends JpaRepository<ReservationBlock, Integer> {
    List<ReservationBlock> findByDateGreaterThanEqual(LocalDate today); // 예약 페이지에서는 전부 보내서 bookable여부에 따라 다르게 보여야 해서 다 보냄.

    List<ReservationBlock> findByDate(LocalDate date);

    ReservationBlock findFirstByDateAndStartTimeAndIsBookableOrderByPlaceIdAsc(LocalDate date, LocalTime startTime, boolean b);

    List<ReservationBlockDto> findByDateGreaterThanEqualAndIsBookableTrue(LocalDate now);

    List<ReservationBlock> findAllReservationBlockByDateAndStartTimeAndIsBookableFalse(LocalDate date, LocalTime startTime);

    ReservationBlock findReservationBlockByPlaceAndDateAndStartTime(Place place, LocalDate date, LocalTime startTime);

    List<ReservationBlock> findByDateGreaterThanEqualAndIsBookableTrueOrderByDateAscStartTimeAsc(LocalDate now);
}