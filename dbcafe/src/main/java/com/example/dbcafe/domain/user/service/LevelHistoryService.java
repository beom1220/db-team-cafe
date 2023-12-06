package com.example.dbcafe.domain.user.service;

import com.example.dbcafe.domain.user.repository.LevelHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LevelHistoryService {
    private final LevelHistoryRepository levelHistoryRepository;
}
