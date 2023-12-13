package com.example.dbcafe.domain.admin.setting;

import com.example.dbcafe.domain.admin.dto.EditLevelDto;
import com.example.dbcafe.domain.admin.dto.LevelDto;
import com.example.dbcafe.domain.admin.dto.LevelTotalDto;
import com.example.dbcafe.domain.order.domain.Orders;
import com.example.dbcafe.domain.order.repository.OrdersRepository;
import com.example.dbcafe.domain.user.domain.Level;
import com.example.dbcafe.domain.user.domain.User;
import com.example.dbcafe.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SettingService {
    private final SettingRepository settingRepository;
    private final UserRepository userRepository;
    private final OrdersRepository ordersRepository;

    public int findValueByName(String name) {
        return settingRepository.findByName(name).getValue();
    }

    public List<LevelDto> getLevelInfo() {
        List<LevelDto> dtos = new ArrayList<>();
        for (Level level : Level.values()) {
            String name = level.getValue();
            List<User> users = userRepository.findAllUserByLevel(level);
            int discountRatio = findValueByName(name + "할인율");
            List<Orders> ordersList = ordersRepository.findAllOrdersByLevelDiscountRatio(discountRatio);
            int totalLevelDiscountAmount = 0;
            for (Orders o : ordersList) {
                totalLevelDiscountAmount += o.getLevelDiscountAmount();
            }
//            int averageDiscountAmount = totalLevelDiscountAmount / ordersList.size();
                        int averageDiscountAmount = totalLevelDiscountAmount / 1;
            int cutLine = findValueByName(name + "기준");
            LevelDto dto = new LevelDto(name, users.size(), totalLevelDiscountAmount,
                    averageDiscountAmount, discountRatio, cutLine);
            dtos.add(dto);
        }
        return dtos;
    }

    public LevelTotalDto getTotalLevelInfo(List<LevelDto> dtos) {
        int totalUserCount = 0;
        int totalDiscountAmount = 0;
        for (LevelDto dto : dtos) {
            totalUserCount += dto.getUserCount();
            totalDiscountAmount += dto.getTotalDiscountAmount();
        }
        List<Orders> ordersList = ordersRepository.findAllOrdersByLevelDiscountRatioNot(0);
        int averageDiscountAmount = totalDiscountAmount / ordersList.size();

        LevelTotalDto levelTotalDto = new LevelTotalDto(totalUserCount,
                totalDiscountAmount, averageDiscountAmount);
        return levelTotalDto;
    }

    public void editLevel(EditLevelDto dto) {
        int levelIndex = dto.getLevelIndex();
        String levelName = null;
        if (levelIndex == 0) {
            levelName = "브론즈";
        } else if (levelIndex == 1) {
            levelName = "실버";
        } else if (levelIndex == 2) {
            levelName = "골드";
        } else if (levelIndex == 3) {
            levelName = "다이아";
        } else {
            levelName = "VIP";
        }
        Setting discount = settingRepository.findByName(levelName + "할인율");
        Setting cutLine = settingRepository.findByName(levelName + "기준");

        discount.setValue(dto.getDiscountRatio());
        cutLine.setValue(dto.getCutLine());

        settingRepository.save(discount);
        settingRepository.save(cutLine);
    }
}
