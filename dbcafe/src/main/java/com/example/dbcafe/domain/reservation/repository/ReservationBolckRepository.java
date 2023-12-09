package com.example.dbcafe.domain.reservation.repository;

import com.example.dbcafe.domain.reservation.domain.ReservationBlock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ReservationBolckRepository extends JpaRepository<ReservationBlock, Integer> {
    List<ReservationBlock> findByDateGreaterThanEqual(LocalDate today);

    List<ReservationBlock> findByDate(LocalDate date);

    ReservationBlock findFirstByDateAndStartTimeAndIsBookableOrderByPlaceIdAsc(LocalDate date, LocalTime startTime, boolean b);
}