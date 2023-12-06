package com.example.dbcafe.domain.reservation.repository;

import com.example.dbcafe.domain.reservation.domain.ReservationBolck;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationBolckRepository extends JpaRepository<ReservationBolck, Integer> {
}