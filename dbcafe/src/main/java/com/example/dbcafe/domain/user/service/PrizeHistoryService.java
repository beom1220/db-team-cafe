package com.example.dbcafe.domain.user.service;

import com.example.dbcafe.domain.user.repository.PrizeHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrizeHistoryService {
    private final PrizeHistoryRepository prizeHistoryRepository;
}
