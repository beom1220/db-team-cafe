package com.example.dbcafe.domain.user.repository;

import com.example.dbcafe.domain.user.domain.OwnCoupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnCouponRepository extends JpaRepository<OwnCoupon, Integer> {
    OwnCoupon findOwnCouponById(int id);
}