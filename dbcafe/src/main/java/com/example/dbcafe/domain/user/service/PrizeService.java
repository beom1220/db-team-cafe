package com.example.dbcafe.domain.user.service;

import com.example.dbcafe.domain.user.domain.Prize;
import com.example.dbcafe.domain.user.domain.User;
import com.example.dbcafe.domain.user.dto.PrizeUserInfoDto;
import com.example.dbcafe.domain.user.repository.PrizeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PrizeService {
    private final PrizeRepository prizeRepository;

    public List<Prize> findAllPrizes() {
        return prizeRepository.findAllPrizes();
    }

    public PrizeUserInfoDto convertToDto(User user) {
        return new PrizeUserInfoDto(user.getPrizeChance(), user.getCoin());
    }

    public Prize draw() {
        List<Prize> prizes = prizeRepository.findAllPrizes();
        List<Integer> prizeBox = new ArrayList<>();
        for (Prize prize : prizes) {
            for (int i = 0; i < prize.getProbability(); i++) {
                prizeBox.add(prize.getId());
            }
        }
        int randomIndex = (int) (Math.random() * prizeBox.size());
        return prizes.get(randomIndex);
    }

    public void settlePrize(Prize prize, User user) {
        user.setPrizeChance(user.getPrizeChance() - 1);
        user.setCoin(user.getCoin() + prize.getCoin() - 1);
        user.setMileage(user.getMileage() + prize.getMileage());
    }
}
