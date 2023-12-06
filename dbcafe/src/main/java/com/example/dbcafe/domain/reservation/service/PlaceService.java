package com.example.dbcafe.domain.reservation.service;

import com.example.dbcafe.domain.reservation.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlaceService {
    private final PlaceRepository placeRepository;
}
