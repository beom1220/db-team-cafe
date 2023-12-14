package com.example.dbcafe.domain.reservation.service;

import com.example.dbcafe.domain.admin.setting.SettingService;
import com.example.dbcafe.domain.reservation.domain.DayOfWeekInKorean;
import com.example.dbcafe.domain.reservation.domain.Reservation;
import com.example.dbcafe.domain.reservation.domain.ReservationBlock;
import com.example.dbcafe.domain.reservation.domain.ReservationItem;
import com.example.dbcafe.domain.reservation.dto.*;
import com.example.dbcafe.domain.reservation.repository.ReservationBlockRepository;
import com.example.dbcafe.domain.reservation.repository.ReservationItemRepository;
import com.example.dbcafe.domain.reservation.repository.ReservationRepository;
import com.example.dbcafe.domain.user.domain.Coupon;
import com.example.dbcafe.domain.user.domain.CouponStatus;
import com.example.dbcafe.domain.user.domain.OwnCoupon;
import com.example.dbcafe.domain.user.domain.User;
import com.example.dbcafe.domain.user.repository.CouponRepository;
import com.example.dbcafe.domain.user.repository.OwnCouponRepository;
import com.example.dbcafe.domain.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final SettingService settingService;
    private final UserService userService;
    private final ReservationItemRepository reservationItemRepository;
    private final ReservationBlockRepository reservationBlockRepository;
    private final CouponRepository couponRepository;
    private final OwnCouponRepository ownCouponRepository;

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

    public List<PackageReservationBlockResponseDto> calPackageDayOfWeekAndDiscountRatio(PackageReservationBlockDto dto){
        List<PackageReservationBlockResponseDto> responseDtos = new ArrayList<>();

        for (LocalDate date = dto.getStartDate(); date.isBefore(dto.getStartDate().plusDays(35));date = date.plusWeeks(1)){
            String dayOfWeek = date.getDayOfWeek().toString();
            int weekdayDiscountRatio = 0;
            int earlybirdDiscountRatio = 0;
            if (!(dayOfWeek.equals("금요일") || dayOfWeek.equals("토요일") || dayOfWeek.equals("일요일"))) {
                weekdayDiscountRatio = settingService.findValueByName("주중할인율");
            }
            if (ChronoUnit.DAYS.between(dto.getStartDate(), LocalDate.now()) >= settingService.findValueByName("얼리버드기준일수")) {
                earlybirdDiscountRatio = settingService.findValueByName("얼리버드할인율");
            }
            PackageReservationBlockResponseDto responseDto = new PackageReservationBlockResponseDto(date, dto.getStartTime(), dto.getEndTime(), dto.getDayOfWeek(), weekdayDiscountRatio, earlybirdDiscountRatio);
            responseDtos.add(responseDto);
        }
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
            ReservationBlock bookableBlock = reservationBlockRepository
                    .findFirstByDateAndStartTimeAndIsBookableOrderByPlaceIdAsc(date, startTime, true);
            ReservationItem item = new ReservationItem(savedReservation, bookableBlock,
                    reservationInfo.getTempPw(), settingService.findValueByName("블록당선결제금액"),
                    block.getEarlybirdDiscountRatio(), block.getWeekdayDiscountRatio(), true);
            ReservationItem lastItem = reservationItemRepository.findByReservationUserAndLast(user, true);
            lastItem.setLast(false);
            reservationItemRepository.save(lastItem);
            bookableBlock.setBookable(false);
            reservationBlockRepository.save(bookableBlock);
            reservationItemRepository.save(item);
        }
    }

    public ReservationItem findItemByIdAndTempPw(int itemId, String tempPw) {
        return reservationItemRepository.findByIdAndTempPw(itemId, tempPw);
    }

    public List<ReservationItemListDto> findAllReservationItem() {
        List<ReservationItem> items = reservationItemRepository.findAllReservationItemByReservationBlockDateGreaterThanEqualOrderByReservationBlockDateAscReservationBlockStartTimeAsc(LocalDate.now());
        List<ReservationItemListDto> dtos = new ArrayList<>();
        for (ReservationItem item : items) {
            Reservation r = item.getReservation();
            ReservationBlock b = item.getReservationBlock();
            ReservationItemListDto dto = new ReservationItemListDto(item.getId(),
                    r.getClassName(), r.getUser().getName(), b.getDate(), b.getStartTime(),
                    r.isCanceled());
            dtos.add(dto);
        }
        return dtos;
    }

    public List<CouponSelectDto> getCouponList() {
        List<Coupon> coupons =  couponRepository.findAll();
        List<CouponSelectDto> dtos = new ArrayList<>();
        for (Coupon c : coupons) {
            CouponSelectDto dto = new CouponSelectDto(c.getId(), c.getName(), c.getDiscountRatio());
            dtos.add(dto);
        }
        return dtos;
    }

    public RejectionFormDto convertToRejectionFormDto(int reservationItemId) {
        ReservationItem reservationItem = reservationItemRepository.findReservationItemById(reservationItemId);
        User user = reservationItem.getReservation().getUser();
        return new RejectionFormDto(user.getId(), user.getName(),
                user.getPhone(), user.getLevel());
    }

    public void adminRejection(ReservationRejectionDto dto) {
        User user = userService.findById(dto.getUserId());
        Coupon coupon = couponRepository.findCouponById(dto.getCouponId());
        OwnCoupon ownCoupon = new OwnCoupon(coupon, user, CouponStatus.USABLE);
        ownCoupon = ownCouponRepository.save(ownCoupon);

        Date date = ownCoupon.getCreatedAt();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate newLocalDate = localDate.plusDays(ownCoupon.getCoupon().getPeriod());
        Date dueDate = Date.from(newLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        ownCoupon.setDueDate(dueDate);
        ownCoupon = ownCouponRepository.save(ownCoupon);

        user.setCoin(user.getCoin() + dto.getCoin());
        user = userService.save(user);

        log.info(user.getId() + "님의 전화번호인 " + user.getPhone() + "으로 다음과 같은 문자 전송");
        log.info(dto.getContent() + " 사과의 의미로 " + dto.getCoin() + "개의 코인과 "
                + coupon.getDiscountRatio() + "%의 할인쿠폰을 제공합니다.");

        ReservationItem item = reservationItemRepository.findReservationItemById(dto.getItemId());
        reservationItemRepository.delete(item);
    }
}
