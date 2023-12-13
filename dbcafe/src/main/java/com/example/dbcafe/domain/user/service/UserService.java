package com.example.dbcafe.domain.user.service;

import com.example.dbcafe.domain.admin.setting.SettingService;
import com.example.dbcafe.domain.order.dto.UserNextLevelDto;
import com.example.dbcafe.domain.reservation.domain.Entrant;
import com.example.dbcafe.domain.reservation.domain.Reservation;
import com.example.dbcafe.domain.reservation.domain.ReservationItem;
import com.example.dbcafe.domain.reservation.domain.ScheduledEvent;
import com.example.dbcafe.domain.reservation.dto.UserSelectDayDto;
import com.example.dbcafe.domain.reservation.repository.ReservationItemRepository;
import com.example.dbcafe.domain.user.domain.Level;
import com.example.dbcafe.domain.user.domain.LevelHistory;
import com.example.dbcafe.domain.user.domain.User;
import com.example.dbcafe.domain.user.dto.KeepUserDto;
import com.example.dbcafe.domain.user.dto.MyPageDto;
import com.example.dbcafe.domain.user.repository.LevelHistoryRepository;
import com.example.dbcafe.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final SettingService settingService;
    private final LevelHistoryRepository levelHistoryRepository;
    private final ReservationItemRepository reservationItemRepository;

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
                user.getPhone(), gender, user.getMileage(), user.getLevel(), user.getCoin(),
                scheduledEvents, reservationItems);
        return dto;
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public UserSelectDayDto convertToSelectDayDto(User user) {
        int term, shortage, newTerm;
        int acc = user.getAccumulation();
        if (acc >= settingService.findValueByName("누적금액1단계기준")) {
            term = 7 * settingService.findValueByName("누적금액1단계기간");
            shortage = 0;
            newTerm = 0;
        } else if (acc >= settingService.findValueByName("누적금액2단계기준")) {
            term = 7 * settingService.findValueByName("누적금액2단계기간");
            shortage = settingService.findValueByName("누적금액1단계기준") - acc;
            newTerm = settingService.findValueByName("누적금액1단계기간");
        } else if (acc >= settingService.findValueByName("누적금액3단계기준")) {
            term = 7 * settingService.findValueByName("누적금액3단계기간");;
            shortage = settingService.findValueByName("누적금액2단계기준") - acc;
            newTerm = settingService.findValueByName("누적금액2단계기간");
        } else {
            term = 14;
            shortage = settingService.findValueByName("누적금액3단계기준") - acc;
            newTerm = settingService.findValueByName("누적금액3단계기간");
        }
        return new UserSelectDayDto(term, shortage, newTerm);
    }

    public UserNextLevelDto convertToNextLevelDto(User user) {
        int currentCoin = user.getCoin();
        int shortage;
        String nextLevel;
        LocalDate today = LocalDate.now();
        int year = today.getYear();
        int month = today.getMonthValue();
        if (month == 1) {
            year -= 1;
            month = 12;
        } else {
            month -= 1;
        }
        LevelHistory vipCutLine = levelHistoryRepository.findFirstByLevelAndYearAndMonthOrderByCoinAsc(Level.VIP, year, month);
        LevelHistory diamondCutLine = levelHistoryRepository.findFirstByLevelAndYearAndMonthOrderByCoinAsc(Level.DIAMOND, year, month);
        LevelHistory goldCutLine = levelHistoryRepository.findFirstByLevelAndYearAndMonthOrderByCoinAsc(Level.GOLD, year, month);
        LevelHistory silverCutLine = levelHistoryRepository.findFirstByLevelAndYearAndMonthOrderByCoinAsc(Level.SILVER, year, month);
        if (currentCoin >= vipCutLine.getCoin()) {
            nextLevel = "";
            shortage = -1;
        } else if (currentCoin >= diamondCutLine.getCoin()) {
            nextLevel = "VIP";
            shortage = vipCutLine.getCoin() - currentCoin;
        } else if (currentCoin >= goldCutLine.getCoin()) {
            nextLevel = "다이아";
            shortage = diamondCutLine.getCoin() - currentCoin;
        } else if (currentCoin >= silverCutLine.getCoin()) {
            nextLevel = "골드";
            shortage = goldCutLine.getCoin() - currentCoin;
        } else {
            nextLevel = "실버";
            shortage = silverCutLine.getCoin() - currentCoin;
        }
        return new UserNextLevelDto(nextLevel, shortage);
    }

    public List<KeepUserDto> findKeepUserInfo() {
        LocalDate today = LocalDate.now();
        LocalDate startDate = today.minusDays(90);
        LocalDate endDate = today.minusDays(30);
        int accCut = 300000; // 누적이용금액 최저 기준
        List<ReservationItem> items = reservationItemRepository.findAllByLastAndKeepingAndReservationBlockDateBetweenAndReservationUserAccumulationGreaterThanEqualOrderByReservationBlockDateAscReservationUserAccumulationDesc(true, false, startDate, endDate, accCut);
        List<KeepUserDto> dtos = new ArrayList<>();
        for (ReservationItem item : items) {
            KeepUserDto dto; // 여기 하던 중이다 고쳐야 된다.
        }

        return dtos;
    }
}
