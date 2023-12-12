package com.example.dbcafe.domain.user.repository;

import com.example.dbcafe.domain.user.domain.Level;
import com.example.dbcafe.domain.user.domain.LevelHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Month;

public interface LevelHistoryRepository extends JpaRepository<LevelHistory, Integer> {
    LevelHistory findFirstByLevelAndYearAndMonthOrderByCoinAsc(Level level, int year, int month);
}