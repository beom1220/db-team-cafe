package com.example.dbcafe.domain.user.service;

import com.example.dbcafe.domain.user.domain.OwnCoupon;
import com.example.dbcafe.domain.user.repository.OwnCouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OwnCouponService {
    private final OwnCouponRepository ownCouponRepository;

    public OwnCoupon findById(int id) {
        return ownCouponRepository.findOwnCouponById(id);
    }
}
