package com.example.dbcafe.domain.user.service;

import com.example.dbcafe.domain.user.domain.PrizeHistory;
import com.example.dbcafe.domain.user.dto.BenefitPrizeHistoryDto;
import com.example.dbcafe.domain.user.dto.PrizeHistoryDto;
import com.example.dbcafe.domain.user.dto.PrizeStatisticsDto;
import com.example.dbcafe.domain.user.repository.PrizeHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PrizeHistoryService {
    private final PrizeHistoryRepository prizeHistoryRepository;

    public PrizeStatisticsDto getStatistic() {
        List<PrizeHistory> items = prizeHistoryRepository.findAll();
        int totalDraw = items.size();
        int totalMileage = 0;
        for (PrizeHistory item : items) {
            totalMileage += item.getPrize().getMileage() + item.getPrize().getCoin() * 500;
        }
        int averageMileage = totalMileage / totalDraw;
        return new PrizeStatisticsDto(totalDraw, totalMileage, averageMileage);

    }

    public List<PrizeHistoryDto> showAllHistory() {
        List<PrizeHistory> items = prizeHistoryRepository.findAll();
        List<PrizeHistoryDto> dtos = new ArrayList<>();
        for (PrizeHistory ph : items) {
            PrizeHistoryDto dto = new PrizeHistoryDto(ph.getUser().getId(),
                    ph.getPrize().getName(), ph.getCreatedAt());
            dtos.add(dto);
        }
        return dtos;
    }

    public List<BenefitPrizeHistoryDto> getBenefitHistory() {
        List<PrizeHistory> coinHistories = prizeHistoryRepository.findTop10ByPrizeCoinGreaterThanEqualOrderByCreatedAtDesc(1);
        List<PrizeHistory> mileageHistories = prizeHistoryRepository.findTop10ByPrizeMileageGreaterThanEqualOrderByCreatedAtDesc(500);

        List<PrizeHistory> combinedList = new ArrayList<>();
        combinedList.addAll(coinHistories);
        combinedList.addAll(mileageHistories);

        Collections.sort(combinedList, Comparator.comparing(PrizeHistory::getCreatedAt).reversed());

        if (combinedList.size() > 10) {
            combinedList = combinedList.subList(0, 10);
        }
        List<BenefitPrizeHistoryDto> dtos = new ArrayList<>();
        for (PrizeHistory h : combinedList) {
            BenefitPrizeHistoryDto dto = new BenefitPrizeHistoryDto(h.getUser().getId(),
                    h.getPrize().getName(), h.getCreatedAt());
            dtos.add(dto);
        }
        return dtos;
    }
}
