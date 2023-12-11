package com.example.dbcafe.domain.user.repository;

import com.example.dbcafe.domain.user.domain.Prize;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrizeRepository extends JpaRepository<Prize, Integer> {
    List<Prize> findAllPrizeByProbabilityNot(int i);

    Prize findPrizeById(int prizeId);
}