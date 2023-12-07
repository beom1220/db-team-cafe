package com.example.dbcafe.domain.reservation.repository;

import com.example.dbcafe.domain.reservation.domain.ReservationBolck;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReservationBolckRepository extends JpaRepository<ReservationBolck, Integer> {
    List<ReservationBolck> findByDateGreaterThanEqual(LocalDate today);
}