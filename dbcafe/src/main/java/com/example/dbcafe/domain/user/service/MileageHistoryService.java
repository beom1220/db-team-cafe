package com.example.dbcafe.domain.user.service;

import com.example.dbcafe.domain.user.repository.MileageHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MileageHistoryService {
    private final MileageHistoryRepository mileageHistoryRepository;
}
