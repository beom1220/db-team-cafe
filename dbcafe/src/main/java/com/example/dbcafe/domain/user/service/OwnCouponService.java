package com.example.dbcafe.domain.user.service;

import com.example.dbcafe.domain.user.repository.OwnCouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OwnCouponService {
    private final OwnCouponRepository ownCouponRepository;
}
