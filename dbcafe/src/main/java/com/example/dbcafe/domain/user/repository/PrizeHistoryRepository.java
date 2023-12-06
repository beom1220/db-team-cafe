package com.example.dbcafe.domain.user.repository;

import com.example.dbcafe.domain.user.domain.PrizeHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrizeHistoryRepository extends JpaRepository<PrizeHistory, Integer> {
}