
package com.example.dbcafe.domain.user.repository;

import com.example.dbcafe.domain.user.domain.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CouponRepository extends JpaRepository<Coupon, Integer> {
    Coupon findCouponById(int couponId);
}