package com.example.dbcafe.domain.reservation.service;

import com.example.dbcafe.domain.admin.setting.SettingService;
import com.example.dbcafe.domain.reservation.domain.*;
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
    private final ReservationCheckerService reservationCheckerService;

    public List<ReservationBlockResponseDto> calDayOfWeekAndDiscountRatio(ReservationBlockRequestDto dto) {
        String dayOfWeek = DayOfWeekInKorean.valueOf(dto.getDate().getDayOfWeek().name()).getDay();
        ReservationBlockResponseDto responseDto = new ReservationBlockResponseDto(dto.getBlockId(), dto.getDate(), dayOfWeek,
                dto.getStartTime(), dto.getEndTime(), calEarlybirdDiscount(dto.getDate()), calWeekdayDiscount(dto.getDate()));
        List<ReservationBlockResponseDto> responseDtos = new ArrayList<>();
        responseDtos.add(responseDto);
        return responseDtos;
    }

    public int calWeekdayDiscount(LocalDate date) {
        String dayOfWeek = DayOfWeekInKorean.valueOf(date.getDayOfWeek().name()).getDay();
        int weekdayDiscountRatio = 0;
        if (!(dayOfWeek.equals("금요일") || dayOfWeek.equals("토요일") || dayOfWeek.equals("일요일"))) {
            weekdayDiscountRatio = settingService.findValueByName("주중할인율");
        }
        return weekdayDiscountRatio;
    }

    public int calEarlybirdDiscount(LocalDate date) {
        int earlybirdDiscountRatio = 0;
        if (ChronoUnit.DAYS.between(LocalDate.now(), date) >= settingService.findValueByName("얼리버드기준일수")) {
            earlybirdDiscountRatio = settingService.findValueByName("얼리버드할인율");
        }
        return earlybirdDiscountRatio;
    }

    public List<PackageReservationBlockResponseDto> calPackageDayOfWeekAndDiscountRatio(int blockId){
        List<PackageReservationBlockResponseDto> responseDtos = new ArrayList<>();
        ReservationBlock block = reservationBlockRepository.findReservationBlockById(blockId);
        String dayOfWeek = DayOfWeekInKorean.valueOf(block.getDate().getDayOfWeek().name()).getDay();
        
        for (LocalDate date = block.getDate(); date.isBefore(block.getDate().plusDays(28)); date = date.plusWeeks(1)){
            PackageReservationBlockResponseDto responseDto = new PackageReservationBlockResponseDto(blockId, date, block.getStartTime(), block.getEndTime(), dayOfWeek,
                    calEarlybirdDiscount(date), calWeekdayDiscount(date));
            responseDtos.add(responseDto);
        }
        return responseDtos;
    }

    public void submitReservation(ReservationRequestDto dto, HttpSession session) {
        User user = userService.findById((String) session.getAttribute("loggedInUser"));
        Reservation reservation = new Reservation(user, dto.getClassName(),
                dto.getNumOfParticipant(), dto.getPrepaymentTotal(),
                dto.getPaymentMethod(), false);
        Reservation savedReservation = reservationRepository.save(reservation);

        for (int blockId : dto.getBlockIds()) {
            ReservationBlock block = reservationBlockRepository.findReservationBlockById(blockId);
            LocalDate date = block.getDate();

            ReservationItem item = new ReservationItem(savedReservation, block,
                    dto.getTempPw(), settingService.findValueByName("블록당선결제금액"),
                    calEarlybirdDiscount(date), calWeekdayDiscount(date), true, block.getDate(), block.getStartTime(), block.getEndTime());
            ReservationItem lastItem = reservationItemRepository.findByReservationUserAndLast(user, true);
            lastItem.setLast(false);
            reservationItemRepository.save(lastItem);
            block.setBookable(false);
            reservationBlockRepository.save(block);
            reservationCheckerService.updateChecker(block);
            reservationItemRepository.save(item);
        }
//        for (ReservationBlockResponseDto block : blocks) {
//            // 시간과 일자로 사용가능한 block을 실제로 찾는 과정
//            LocalDate date = block.getDate();
//            LocalTime startTime = block.getStartTime();
//            ReservationBlock bookableBlock = reservationBlockRepository
//                    .findFirstByDateAndStartTimeAndIsBookableOrderByPlaceIdAsc(date, startTime, true);
//            ReservationItem item = new ReservationItem(savedReservation, bookableBlock,
//                    reservationInfo.getTempPw(), settingService.findValueByName("블록당선결제금액"),
//                    block.getEarlybirdDiscountRatio(), block.getWeekdayDiscountRatio(), true);
//            ReservationItem lastItem = reservationItemRepository.findByReservationUserAndLast(user, true);
//            lastItem.setLast(false);
//            reservationItemRepository.save(lastItem);
//            bookableBlock.setBookable(false);
//            reservationBlockRepository.save(bookableBlock);
//            reservationItemRepository.save(item);
//        }
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
        coupon.setIssuance(coupon.getIssuance() + 1);
        couponRepository.save(coupon);

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

    public ReservationBlockRequestDto convertToBlockRequestDto(int blockId) {
        ReservationBlock block = reservationBlockRepository.findReservationBlockById(blockId);
        return new ReservationBlockRequestDto(blockId, block.getDate(), block.getStartTime(), block.getEndTime());
    }

    public void submitPackageReservation(ReservationRequestDto dto, HttpSession session) {
        User user = userService.findById((String) session.getAttribute("loggedInUser"));
        Reservation reservation = new Reservation(user, dto.getClassName(),
                dto.getNumOfParticipant(), dto.getPrepaymentTotal(),
                dto.getPaymentMethod(), true);
        Reservation savedReservation = reservationRepository.save(reservation);

        ReservationBlock basedBlock = reservationBlockRepository.findReservationBlockById(dto.getBlockIds().get(0));
        LocalDate startDate = basedBlock.getDate();
        LocalTime startTime = basedBlock.getStartTime();

        for (int i = 0; i <= 21; i += 7) {
            ReservationBlock packageBlock = reservationBlockRepository.findFirstByDateAndStartTimeAndIsBookableOrderByPlaceIdAsc(startDate.plusDays(i), startTime, true);
            ReservationItem item = new ReservationItem(savedReservation, packageBlock, dto.getTempPw(),
                    settingService.findValueByName("블록당선결제금액"), calEarlybirdDiscount(packageBlock.getDate()),
                    calWeekdayDiscount(packageBlock.getDate()), true);
            ReservationItem lastItem = reservationItemRepository.findByReservationUserAndLast(user, true);
            lastItem.setLast(false);
            reservationItemRepository.save(lastItem);
            packageBlock.setBookable(false);
            reservationBlockRepository.save(packageBlock);
            reservationCheckerService.updateChecker(packageBlock);
            reservationItemRepository.save(item);
        }
    }
}
