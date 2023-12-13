package com.example.dbcafe.domain.user.repository;

import com.example.dbcafe.domain.user.domain.PrizeHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrizeHistoryRepository extends JpaRepository<PrizeHistory, Integer> {
    List<PrizeHistory> findTop10ByPrizeCoinGreaterThanEqualOrderByCreatedAtDesc(int i);

    List<PrizeHistory> findTop10ByPrizeMileageGreaterThanEqualOrderByCreatedAtDesc(int i);
}