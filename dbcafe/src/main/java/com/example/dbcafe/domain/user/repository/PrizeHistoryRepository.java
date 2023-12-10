package com.example.dbcafe.domain.user.repository;

import com.example.dbcafe.domain.user.domain.PrizeHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrizeHistoryRepository extends JpaRepository<PrizeHistory, Integer> {
    List<PrizeHistory> findAllPrizeHistory();
}