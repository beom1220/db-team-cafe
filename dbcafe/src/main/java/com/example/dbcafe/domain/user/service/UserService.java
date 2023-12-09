package com.example.dbcafe.domain.user.service;

import com.example.dbcafe.domain.admin.setting.SettingService;
import com.example.dbcafe.domain.reservation.domain.Entrant;
import com.example.dbcafe.domain.reservation.domain.Reservation;
import com.example.dbcafe.domain.reservation.domain.ReservationItem;
import com.example.dbcafe.domain.reservation.domain.ScheduledEvent;
import com.example.dbcafe.domain.user.domain.Level;
import com.example.dbcafe.domain.user.domain.User;
import com.example.dbcafe.domain.user.dto.MyPageDto;
import com.example.dbcafe.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final SettingService settingService;

    public User findById(String id) {
        return userRepository.findUserById(id);
    }

    public int findLevelDiscountRatio(Level level) {
        return settingService.findValueByName(level.getValue() + "할인율");
    }

    public MyPageDto convertoToMyPageDto(User user) {
        String gender = "남";
        if (!user.isMale()) {
            gender = "여";
        }
        List<Entrant> entrants = user.getEntrants();
        List<Reservation> reservations = user.getReservations();
        List<ScheduledEvent> scheduledEvents = new ArrayList<>();
        List<ReservationItem> reservationItems = new ArrayList<>();
        for (Entrant entrant : entrants) {
            scheduledEvents.add(entrant.getScheduledEvent());
        }
        for (Reservation reservation : reservations) {
            List<ReservationItem> items = reservation.getReservationItems();
            for (ReservationItem item : items) {
                reservationItems.add(item);
            }
        }
        MyPageDto dto = new MyPageDto(user.getId(), user.getName(),
                user.getPhone(), gender, user.getMileage(), user.getCoin(),
                scheduledEvents, reservationItems);
        return dto;
    }

    public User save(User user) {
        return userRepository.save(user);
    }
}
