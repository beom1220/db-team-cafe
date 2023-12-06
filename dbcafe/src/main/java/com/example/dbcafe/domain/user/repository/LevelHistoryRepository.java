package com.example.dbcafe.domain.user.repository;

import com.example.dbcafe.domain.user.domain.LevelHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LevelHistoryRepository extends JpaRepository<LevelHistory, Integer> {
}