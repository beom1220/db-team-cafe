package com.example.dbcafe.domain.reservation.repository;

import com.example.dbcafe.domain.reservation.domain.ReservationChecker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReservationCheckerRepository extends JpaRepository<ReservationChecker, Integer> {
    ReservationChecker findReservationCheckerByDate(LocalDate date);
}