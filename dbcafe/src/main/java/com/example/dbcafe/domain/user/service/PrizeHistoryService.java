package com.example.dbcafe.domain.user.service;

import com.example.dbcafe.domain.user.domain.PrizeHistory;
import com.example.dbcafe.domain.user.dto.PrizeHistoryDto;
import com.example.dbcafe.domain.user.dto.PrizeStatisticsDto;
import com.example.dbcafe.domain.user.repository.PrizeHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PrizeHistoryService {
    private final PrizeHistoryRepository prizeHistoryRepository;

    public PrizeStatisticsDto getStatistic() {
        List<PrizeHistory> items = prizeHistoryRepository.findAllPrizeHistory();
        int totalDraw = items.size();
        int totalMileage = 0;
        for (PrizeHistory item : items) {
            totalMileage += item.getPrize().getMileage() + item.getPrize().getCoin() * 500;
        }
        int averageMileage = totalMileage / totalDraw;
        return new PrizeStatisticsDto(totalDraw, totalMileage, averageMileage);

    }

    public List<PrizeHistoryDto> showAllHistory() {
        List<PrizeHistory> items = prizeHistoryRepository.findAllPrizeHistory();
        List<PrizeHistoryDto> dtos = new ArrayList<>();
        for (PrizeHistory ph : items) {
            PrizeHistoryDto dto = new PrizeHistoryDto(ph.getUser().getId(),
                    ph.getPrize().getName(), ph.getCreatedAt());
            dtos.add(dto);
        }
        return dtos;
    }
}
