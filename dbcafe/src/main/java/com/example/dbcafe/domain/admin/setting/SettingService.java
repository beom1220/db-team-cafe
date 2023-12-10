package com.example.dbcafe.domain.admin.setting;

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
            int discountRatio = settingRepository.findByName(name + "할인율").getValue();
            List<Orders> ordersList = ordersRepository.findAllOrdersByLevelDiscountRatio(discountRatio);
            int totalLevelDiscountAmount = 0;
            for (Orders o : ordersList) {
                totalLevelDiscountAmount += o.getLevelDiscountAmount();
            }
            int averageDiscountAmount = totalLevelDiscountAmount / ordersList.size();
            int cutLine = settingRepository.findByName(name + "기준").getValue();
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
}
