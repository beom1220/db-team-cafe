package com.example.dbcafe.domain.user.service;

import com.example.dbcafe.domain.user.domain.Prize;
import com.example.dbcafe.domain.user.domain.User;
import com.example.dbcafe.domain.user.dto.PrizeDto;
import com.example.dbcafe.domain.user.dto.PrizeListDto;
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

    public List<PrizeListDto> findAllPrizes() {
        List<Prize> prizes =  prizeRepository.findAllPrizes();
        List<PrizeListDto> dtos = new ArrayList<>();

        for (Prize p : prizes) {
            boolean isCoin = false;
            int value = p.getMileage();
            if (p.getCoin() > 0) {
                isCoin = true;
                value = p.getCoin();
            }
            PrizeListDto dto = new PrizeListDto(p.getId(),
                    p.getName(), isCoin, value, p.getProbability());
            dtos.add(dto);
        }
        return dtos;
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

    public List<Prize> findAllDrawablePrizes() {
        return prizeRepository.findAllPrizeByProbabilityNot(0);
    }

    public Prize addPrize(PrizeDto dto) {
        Prize prize;
        if (dto.isCoin()) {
            prize = new Prize(dto.getName(), 0, dto.getValue(), dto.getProbability());
        } else {
            prize = new Prize(dto.getName(), dto.getValue(), 0, dto.getProbability());
        }
        return prizeRepository.save(prize);
    }

    public Prize editPrize(int prizeId, PrizeDto dto) {
        Prize prize = prizeRepository.findPrizeById(prizeId);
        if (dto.isCoin()) {
            prize.setName(dto.getName());
            prize.setCoin(dto.getValue());
            prize.setMileage(0);
            prize.setProbability(dto.getProbability());
        } else {
            prize.setName(dto.getName());
            prize.setCoin(0);
            prize.setMileage(dto.getValue());
            prize.setProbability(dto.getProbability());
        }
        return prizeRepository.save(prize);
    }
}
