package com.example.dbcafe.domain.user.repository;

import com.example.dbcafe.domain.user.domain.Prize;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrizeRepository extends JpaRepository<Prize, Integer> {
}