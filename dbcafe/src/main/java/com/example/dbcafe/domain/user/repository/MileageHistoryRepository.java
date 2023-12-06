package com.example.dbcafe.domain.user.repository;

import com.example.dbcafe.domain.user.domain.MileageHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MileageHistoryRepository extends JpaRepository<MileageHistory, Integer> {
}