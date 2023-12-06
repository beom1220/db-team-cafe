package com.example.dbcafe.domain.user.service;

import com.example.dbcafe.domain.user.repository.PrizeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrizeService {
    private final PrizeRepository prizeRepository;
}
