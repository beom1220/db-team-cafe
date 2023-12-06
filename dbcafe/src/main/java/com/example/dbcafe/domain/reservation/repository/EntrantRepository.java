package com.example.dbcafe.domain.reservation.repository;

import com.example.dbcafe.domain.reservation.domain.Entrant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntrantRepository extends JpaRepository<Entrant, Integer> {
}