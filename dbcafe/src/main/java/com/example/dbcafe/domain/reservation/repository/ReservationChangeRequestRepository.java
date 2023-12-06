package com.example.dbcafe.domain.reservation.repository;

import com.example.dbcafe.domain.reservation.domain.ReservationChangeRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationChangeRequestRepository extends JpaRepository<ReservationChangeRequest, Integer> {
}