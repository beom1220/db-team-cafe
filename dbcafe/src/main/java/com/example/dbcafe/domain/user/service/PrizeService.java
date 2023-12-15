package com.example.dbcafe.domain.user.service;

import com.example.dbcafe.domain.user.domain.MileageHistory;
import com.example.dbcafe.domain.user.domain.Prize;
import com.example.dbcafe.domain.user.domain.PrizeHistory;
import com.example.dbcafe.domain.user.domain.User;
import com.example.dbcafe.domain.user.dto.PrizeDto;
import com.example.dbcafe.domain.user.dto.PrizeListDto;
import com.example.dbcafe.domain.user.dto.PrizeUserInfoDto;
import com.example.dbcafe.domain.user.repository.MileageHistoryRepository;
import com.example.dbcafe.domain.user.repository.PrizeHistoryRepository;
import com.example.dbcafe.domain.user.repository.PrizeRepository;
import com.example.dbcafe.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PrizeService {
    private final PrizeRepository prizeRepository;
    private final UserRepository userRepository;
    private final MileageHistoryRepository mileageHistoryRepository;

    private final PrizeHistoryRepository prizeHistoryRepository;
    public List<PrizeListDto> findAllPrizes() {
        List<Prize> prizes =  prizeRepository.findAll();
        List<PrizeListDto> dtos = new ArrayList<>();

        for (Prize p : prizes) {
            boolean isCoin = false;
            int value = p.getMileage();
            if (p.getCoin() > 0) {
                isCoin = true;
                value = p.getCoin();
            }
            List<PrizeHistory> histories = prizeHistoryRepository.findAllByPrize(p);
            PrizeListDto dto = new PrizeListDto(p.getId(),
                    p.getName(), isCoin, value, p.getProbability(), histories.size());
            dtos.add(dto);
        }
        return dtos;
    }

    public PrizeUserInfoDto convertToDto(User user) {
        return new PrizeUserInfoDto(user.getPrizeChance(), user.getCoin());
    }

    public Prize draw() {
        List<Prize> prizes = prizeRepository.findAll();
        List<Integer> prizeBox = new ArrayList<>();
        for (Prize prize : prizes) {
            for (int i = 0; i < prize.getProbability(); i++) {
                prizeBox.add(prize.getId());
            }
        }
        int randomIndex = (int) (Math.random() * prizeBox.size());
        return prizeRepository.findPrizeById(prizeBox.get(randomIndex));
    }

    public void settlePrize(Prize prize, User user) {
        user.setPrizeChance(user.getPrizeChance() - 1);
        user.setCoin(user.getCoin() + prize.getCoin() - 1);
        user.setMileage(user.getMileage() + prize.getMileage());
        prizeHistoryRepository.save(new PrizeHistory(user, prize));
        if (prize.getMileage() > 0) {
            mileageHistoryRepository.save(new MileageHistory(user, false, prize.getMileage(), "뽑기로 획득"));
        }
        userRepository.save(user);
    }

    public List<Prize> findAllDrawablePrizes() {
        return prizeRepository.findAllPrizeByProbabilityNot(0);
    }

    public Prize addPrize(PrizeDto dto) {
        Prize prize;
        if (dto.isCoin()) {
            String name = dto.getValue() + "코인";
            prize = new Prize(name, 0, dto.getValue(), dto.getProbability());
        } else {
            String name = dto.getValue() + "포인트";
            prize = new Prize(name, dto.getValue(), 0, dto.getProbability());
        }
        return prizeRepository.save(prize);
    }

    public Prize editPrize(int prizeId, PrizeDto dto) {
        Prize prize = prizeRepository.findPrizeById(prizeId);
        if (dto.isCoin()) {
            String name = dto.getValue() + "코인";
            prize.setName(name);
            prize.setCoin(dto.getValue());
            prize.setMileage(0);
            prize.setProbability(dto.getProbability());
        } else {
            String name = dto.getValue() + "포인트";
            prize.setName(name);
            prize.setCoin(0);
            prize.setMileage(dto.getValue());
            prize.setProbability(dto.getProbability());
        }
        return prizeRepository.save(prize);
    }
}
