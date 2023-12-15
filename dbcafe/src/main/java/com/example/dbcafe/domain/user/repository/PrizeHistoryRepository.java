package com.example.dbcafe.domain.user.repository;

import com.example.dbcafe.domain.user.domain.Prize;
import com.example.dbcafe.domain.user.domain.PrizeHistory;
import com.example.dbcafe.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrizeHistoryRepository extends JpaRepository<PrizeHistory, Integer> {
    List<PrizeHistory> findTop10ByPrizeCoinGreaterThanEqualOrderByCreatedAtDesc(int i);

    List<PrizeHistory> findTop10ByPrizeMileageGreaterThanEqualOrderByCreatedAtDesc(int i);

    PrizeHistory findFirstByUserOrderByCreatedAtDesc(User user);

    List<PrizeHistory> findAllByOrderByCreatedAtDesc();

    List<PrizeHistory> findAllByPrize(Prize p);
}