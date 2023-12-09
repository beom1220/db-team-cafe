package com.example.dbcafe.domain.reservation.service;

import com.example.dbcafe.domain.admin.setting.SettingService;
import com.example.dbcafe.domain.reservation.domain.DayOfWeekInKorean;
import com.example.dbcafe.domain.reservation.domain.Reservation;
import com.example.dbcafe.domain.reservation.domain.ReservationBlock;
import com.example.dbcafe.domain.reservation.domain.ReservationItem;
import com.example.dbcafe.domain.reservation.dto.ReservationBlockRequestDto;
import com.example.dbcafe.domain.reservation.dto.ReservationBlockResponseDto;
import com.example.dbcafe.domain.reservation.dto.ReservationRequestDto;
import com.example.dbcafe.domain.reservation.repository.ReservationBolckRepository;
import com.example.dbcafe.domain.reservation.repository.ReservationItemRepository;
import com.example.dbcafe.domain.reservation.repository.ReservationRepository;
import com.example.dbcafe.domain.user.domain.User;
import com.example.dbcafe.domain.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final SettingService settingService;
    private final UserService userService;
    private final ReservationItemRepository reservationItemRepository;
    private final ReservationBolckRepository reservationBolckRepository;

    public List<ReservationBlockResponseDto> calDayOfWeekAndDiscountRatio(ReservationBlockRequestDto dto) {
        String dayOfWeek = DayOfWeekInKorean.valueOf(dto.getDate().getDayOfWeek().name()).getDay();
        int weekdayDiscountRatio = 0;
        int earlybirdDiscountRatio = 0;
        if (!(dayOfWeek.equals("금요일") || dayOfWeek.equals("토요일") || dayOfWeek.equals("일요일"))) {
            weekdayDiscountRatio = settingService.findValueByName("주중할인율");
        }
        if (ChronoUnit.DAYS.between(dto.getDate(), LocalDate.now()) >= settingService.findValueByName("얼리버드기준일수")) {
            earlybirdDiscountRatio = settingService.findValueByName("얼리버드할인율");
        }
        ReservationBlockResponseDto responseDto = new ReservationBlockResponseDto(dto.getDate(), dayOfWeek, dto.getStartTime(), dto.getEndTime(), earlybirdDiscountRatio, weekdayDiscountRatio);
        List<ReservationBlockResponseDto> responseDtos = new ArrayList<>();
        responseDtos.add(responseDto);
        return responseDtos;
    }

    public void submitReservation(ReservationRequestDto reservationInfo, List<ReservationBlockResponseDto> blocks, HttpSession session) {
        User user = userService.findById((String) session.getAttribute("loggedInUser"));
        Reservation reservation = new Reservation(user, reservationInfo.getClassName(),
                reservationInfo.getNumOfParticipant(), reservationInfo.getPrepaymentTotal(),
                reservationInfo.getPaymentMethod(), false);
        Reservation savedReservation = reservationRepository.save(reservation);

        for (ReservationBlockResponseDto block : blocks) {
            // 시간과 일자로 사용가능한 block을 실제로 찾는 과정
            LocalDate date = block.getDate();
            LocalTime startTime = block.getStartTime();
            ReservationBlock bookableBlock = reservationBolckRepository
                    .findFirstByDateAndStartTimeAndIsBookableOrderByPlaceIdAsc(date, startTime, true);
            ReservationItem item = new ReservationItem(savedReservation, bookableBlock,
                    reservationInfo.getTempPw(), settingService.findValueByName("블록당선결제금액"),
                    block.getEarlybirdDiscountRatio(), block.getWeekdayDiscountRatio());
            reservationItemRepository.save(item);
        }
    }

    public ReservationItem findItemByIdAndTempPw(int itemId, String tempPw) {
        return reservationItemRepository.findByIdAndTempPw(itemId, tempPw);
    }
}
