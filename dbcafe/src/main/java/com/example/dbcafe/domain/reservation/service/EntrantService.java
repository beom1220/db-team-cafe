package com.example.dbcafe.domain.reservation.service;

import com.example.dbcafe.domain.reservation.repository.EntrantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EntrantService {
    private final EntrantRepository entrantRepository;
}