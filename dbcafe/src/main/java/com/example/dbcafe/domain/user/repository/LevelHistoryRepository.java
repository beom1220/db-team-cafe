package com.example.dbcafe.domain.user.repository;

import com.example.dbcafe.domain.user.domain.Level;
import com.example.dbcafe.domain.user.domain.LevelHistory;
import com.example.dbcafe.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Month;
import java.util.List;

public interface LevelHistoryRepository extends JpaRepository<LevelHistory, Integer> {
    LevelHistory findFirstByLevelAndYearAndMonthOrderByCoinAsc(Level level, int year, int month);

    List<LevelHistory> findAllByUser(User user);

    LevelHistory findFirstByUserAndLevel(User user, Level level);
}