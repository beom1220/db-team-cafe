package com.example.dbcafe.controller;

import com.example.dbcafe.domain.admin.setting.Setting;
import com.example.dbcafe.domain.admin.setting.SettingRepository;
import com.example.dbcafe.domain.order.domain.*;
import com.example.dbcafe.domain.order.repository.*;
import com.example.dbcafe.domain.reservation.domain.*;
import com.example.dbcafe.domain.reservation.repository.*;
import com.example.dbcafe.domain.user.domain.*;
import com.example.dbcafe.domain.user.repository.*;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DbInitializerService {
    private final MenuRepository menuRepository;
    private final UserRepository userRepository;
    private final ScheduledEventRepository scheduledEventRepository;
    private final EventRepository eventRepository;
    private final PlaceRepository placeRepository;
    private final SettingRepository settingRepository;
    private final OrdersRepository ordersRepository;
    private final OrdersItemRepository ordersItemRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final EntrantRepository entrantRepository;
    private final ReservationRepository reservationRepository;
    private final ReservationBlockRepository reservationBlockRepository;
    private final ReservationChangeRequestRepository reservationChangeRequestRepository;
    private final ReservationItemRepository reservationItemRepository;
    private final CouponRepository couponRepository;
    private final LevelHistoryRepository levelHistoryRepository;
    private final MileageHistoryRepository mileageHistoryRepository;
    private final OwnCouponRepository ownCouponRepository;
    private final PrizeHistoryRepository prizeHistoryRepository;
    private final PrizeRepository prizeRepository;
    private final SuggestionRepository suggestionRepository;
    private final VoucherRepository voucherRepository;

    public void MenuEntity(){
        List<Menu> menuList = new ArrayList<>();
        menuList.add(new Menu("아메리카노", 1500, MenuCategory.COFFEE, "진한 원두향기", true, null));
        menuList.add(new Menu("라떼", 2800, MenuCategory.COFFEE, "우유 향기", true, null));
        menuList.add(new Menu("민트초코프라페", 5000, MenuCategory.BEVERAGE, "민초 매니아", true, null));
        menuList.add(new Menu("쌍화탕", 7000, MenuCategory.TEA, "환절기 조심하세요", true, null));
        menuList.add(new Menu("율무차", 2000, MenuCategory.TEA, "왠지 공부가 잘 될거같은 율무차", true, null));
        menuList.add(new Menu("거대빼빼로", 10000, MenuCategory.SNACK, "못받은 당신을 위한 큰 마음", true, null));
        menuList.add(new Menu("초코파이", 3000, MenuCategory.SNACK, "눈물젖은 그맛", true, null));

        menuRepository.saveAll(menuList);
    }

    public void UserEntity(){
        List<User> userList = new ArrayList<>();

        userRepository.save(new User("001", null, "1111", "신민섭", "010-4543-6156", 24, true, 700, 40000, 4, 5, Level.BRONZE));
        //userList.add(new User("001", null, "1111", "신민섭", "010-4543-6156", 24, true, 0, 0, 0, 5, Level.BRONZE));
        userList.add(new User("002", userRepository.findUserById("001"), "1111", "김기범", "010-1111-2222", 24, true, 500, 500000, 50, 5, Level.DIAMOND));
        userList.add(new User("003", userRepository.findUserById("001"), "1111", "이수빈", "010-1234-5678", 24, false, 50000, 300000, 30, 5, Level.GOLD));
        userList.add(new User("004", userRepository.findUserById("001"), "1111", "손민우", "010-2333-4444", 24, true, 2000, 100000, 10, 5, Level.SILVER));
        userList.add(new User("005", userRepository.findUserById("001"), "1111", "장미루", "010-1199-2222", 24, true, 3000, 90000, 9, 5, Level.SILVER));
        userList.add(new User("006", userRepository.findUserById("001"), "1111", "김주원", "010-4455-1122", 24, true, 4000, 80000, 8, 5, Level.SILVER));

        userList.add(new User("007", userRepository.findUserById("001"), "1111", "수한무", "010-1111-1122", 24, true, 2000, 250000, 25, 5, Level.GOLD));
        userList.add(new User("008", userRepository.findUserById("001"), "1111", "거북이", "010-4632-1122", 24, true, 1000, 270000, 27, 5, Level.GOLD));
        userList.add(new User("009", userRepository.findUserById("001"), "1111", "두루미", "010-5326-1122", 24, true, 1000, 320000, 32, 5, Level.GOLD));
        userList.add(new User("010", userRepository.findUserById("001"), "1111", "동방삭", "010-2754-1122", 24, true, 1000, 700000, 70, 5, Level.DIAMOND));
        userList.add(new User("011", userRepository.findUserById("001"), "1111", "토끼", "010-5678-1122", 24, true, 1000, 20000, 2, 5, Level.BRONZE));
        userList.add(new User("012", userRepository.findUserById("001"), "1111", "자라", "010-4685-1122", 24, true, 1000, 50000, 5, 5, Level.SILVER));
        userList.add(new User("013", userRepository.findUserById("001"), "1111", "도라에몽", "010-4685-1122", 24, true, 1000, 30000, 3, 5, Level.BRONZE));
        userList.add(new User("014", userRepository.findUserById("001"), "1111", "노진구", "010-4685-1122", 24, true, 1000, 800000, 8, 5, Level.DIAMOND));
        userList.add(new User("015", userRepository.findUserById("001"), "1111", "짱구", "010-4685-1122", 24, true, 1000, 3000000, 300, 5, Level.VIP));

        userRepository.saveAll(userList);
    }

    public void ScheduledEventEntity(){
        List<ScheduledEvent> scheduledEventList = new ArrayList<>();

        scheduledEventList.add(new ScheduledEvent(eventRepository.findEventById(1), placeRepository.findPlaceById(1),
                LocalDate.of(2024, 1, 2), LocalTime.of(12,0), LocalTime.of(14,0),
                false, Tag.BEST));
        scheduledEventList.add(new ScheduledEvent(eventRepository.findEventById(2), placeRepository.findPlaceById(2),
                LocalDate.of(2024, 1, 15), LocalTime.of(12,0), LocalTime.of(14,0),
                false, Tag.NEW));
        scheduledEventList.add(new ScheduledEvent(eventRepository.findEventById(3), placeRepository.findPlaceById(3),
                LocalDate.of(2024, 2, 5), LocalTime.of(12,0), LocalTime.of(14,0),
                false, Tag.NOTHING));

        scheduledEventRepository.saveAll(scheduledEventList);
    }

    public void EventEntity(){
        List<Event> eventList = new ArrayList<>();

        eventList.add(new Event("바리스타 체험", "직접 바리스타가 되어보세요.", true, 10, 15000, "사진", 1000, 4.5));
        eventList.add(new Event("신메뉴 테스트", "생각중인 신메뉴를 테스트할 기회!", true, 10, 30000, "사진", 600, 4.9));
        eventList.add(new Event("독서 골든벨", "책을 많이 읽었다면 도전하세요.", true, 20, 3000, "사진", 3000, 4.2));

        eventRepository.saveAll(eventList);
    }

    public void PlaceEntity(){
        List<Place> placeList = new ArrayList<>();

        placeList.add(new Place(30, true, true, 8, false));
        placeList.add(new Place(20, true, true, 6, true));
        placeList.add(new Place(15, true, true, 4, false));
        placeList.add(new Place(25, true, true, 4, true));
        placeList.add(new Place(10, true, true, 2, true));

        placeRepository.saveAll(placeList);
    }

    public void SettingEntity(){ // 값 잘 몰라서 확인 요망
        List<Setting> settingList = new ArrayList<>();

        settingList.add(new Setting(1, "브론즈할인율", "내용", 3));
        settingList.add(new Setting(2, "실버할인율", "내용", 5));
        settingList.add(new Setting(3, "골드할인율", "내용", 10));
        settingList.add(new Setting(4, "다이아할인율", "내용", 20));
        settingList.add(new Setting(5, "VIP할인율", "내용", 30));
        settingList.add(new Setting(6, "주중할인율", "내용", 5));
        settingList.add(new Setting(7, "얼리버드할인율", "내용", 5));
        settingList.add(new Setting(8, "블록당선결제금액", "내용", 50000)); // 5만원
        settingList.add(new Setting(9, "얼리버드기준일수", "내용", 14)); // 2주일
        settingList.add(new Setting(10, "누적금액1단계기준", "내용", 2000000));
        settingList.add(new Setting(11, "누적금액2단계기준", "내용", 1000000));
        settingList.add(new Setting(12, "누적금액3단계기준", "내용", 300000));
        settingList.add(new Setting(13, "누적금액1단계기간", "내용", 8));
        settingList.add(new Setting(14, "누적금액2단계기간", "내용", 6));
        settingList.add(new Setting(15, "누적금액3단계기간", "내용", 4));

        settingRepository.saveAll(settingList);
    }

    public void OrdersEntity(){
        List<Orders> ordersList = new ArrayList<>();
        ordersList.add(new Orders(userRepository.findUserById("001"), PaymentMethod.KAKAO, 10000, OrderStatus.COMPLETE,
                false, 4000, settingRepository.findByName("주중할인율").getValue(),
                500, settingRepository.findByName("브론즈할인율").getValue(), 300, 0, 5200));
        ordersList.add(new Orders(userRepository.findUserById("002"), PaymentMethod.CREDIT, 30000, OrderStatus.PREPARING,
                false, 0, settingRepository.findByName("주중할인율").getValue(),
                1500, 20, 6000, 0, 22500));
        ordersList.add(new Orders(userRepository.findUserById("003"), PaymentMethod.CASH, 8000, OrderStatus.CANCELING,
                false, 0, settingRepository.findByName("주중할인율").getValue(),
                400, 10, 800, 0, 5500));
        ordersList.add(new Orders(userRepository.findUserById("004"), PaymentMethod.KAKAO, 15000, OrderStatus.PREPARING,
                false, 4000, settingRepository.findByName("주중할인율").getValue(),
                750, settingRepository.findByName("실버할인율").getValue(), 750, 0, 13500));
        ordersRepository.saveAll(ordersList);
    }

    public void OrdersItemEntity(){
        List<OrdersItem> ordersItemList = new ArrayList<>();

        ordersItemList.add(new OrdersItem(ordersRepository.findOrdersById(1), menuRepository.findMenuById(3), 2));
        ordersItemList.add(new OrdersItem(ordersRepository.findOrdersById(2), menuRepository.findMenuById(4), 4));
        ordersItemList.add(new OrdersItem(ordersRepository.findOrdersById(2), menuRepository.findMenuById(5), 1));
        ordersItemList.add(new OrdersItem(ordersRepository.findOrdersById(3), menuRepository.findMenuById(5), 4));
        ordersItemList.add(new OrdersItem(ordersRepository.findOrdersById(4), menuRepository.findMenuById(1), 10));

        ordersItemRepository.saveAll(ordersItemList);
    }

    public void CartEntity(){
        List<Cart> cartList = new ArrayList<>();

        cartList.add(new Cart(userRepository.findUserById("001")));
        cartList.add(new Cart(userRepository.findUserById("002")));
        cartList.add(new Cart(userRepository.findUserById("003")));
        cartList.add(new Cart(userRepository.findUserById("004")));
        cartList.add(new Cart(userRepository.findUserById("005")));
        cartList.add(new Cart(userRepository.findUserById("006")));

        cartRepository.saveAll(cartList);
    }

    public void CartItemEntity(){
        List<CartItem> cartItemList = new ArrayList<>();

        cartItemList.add(new CartItem(cartRepository.findByUser(userRepository.findUserById("001")), menuRepository.findMenuById(1), 4));
        cartItemList.add(new CartItem(cartRepository.findByUser(userRepository.findUserById("001")), menuRepository.findMenuById(2), 2));
        cartItemList.add(new CartItem(cartRepository.findByUser(userRepository.findUserById("001")), menuRepository.findMenuById(3), 5));
        cartItemList.add(new CartItem(cartRepository.findByUser(userRepository.findUserById("001")), menuRepository.findMenuById(4), 2));
        cartItemList.add(new CartItem(cartRepository.findByUser(userRepository.findUserById("001")), menuRepository.findMenuById(5), 5));
        cartItemList.add(new CartItem(cartRepository.findByUser(userRepository.findUserById("001")), menuRepository.findMenuById(2), 2));
        cartItemList.add(new CartItem(cartRepository.findByUser(userRepository.findUserById("002")), menuRepository.findMenuById(1), 4));
        cartItemList.add(new CartItem(cartRepository.findByUser(userRepository.findUserById("002")), menuRepository.findMenuById(3), 3));
        cartItemList.add(new CartItem(cartRepository.findByUser(userRepository.findUserById("003")), menuRepository.findMenuById(5), 1));
        cartItemList.add(new CartItem(cartRepository.findByUser(userRepository.findUserById("004")), menuRepository.findMenuById(2), 2));
        cartItemList.add(new CartItem(cartRepository.findByUser(userRepository.findUserById("004")), menuRepository.findMenuById(1), 6));
        cartItemList.add(new CartItem(cartRepository.findByUser(userRepository.findUserById("005")), menuRepository.findMenuById(4), 4));
        cartItemList.add(new CartItem(cartRepository.findByUser(userRepository.findUserById("006")), menuRepository.findMenuById(5), 2));
        cartItemList.add(new CartItem(cartRepository.findByUser(userRepository.findUserById("007")), menuRepository.findMenuById(5), 2));
        cartItemList.add(new CartItem(cartRepository.findByUser(userRepository.findUserById("008")), menuRepository.findMenuById(5), 2));
        cartItemList.add(new CartItem(cartRepository.findByUser(userRepository.findUserById("009")), menuRepository.findMenuById(5), 2));
        cartItemList.add(new CartItem(cartRepository.findByUser(userRepository.findUserById("010")), menuRepository.findMenuById(5), 2));

        cartItemRepository.saveAll(cartItemList);
    }

    public void EntrantEntity(){
        List<Entrant> entrantList = new ArrayList<>();
        User nowuser = userRepository.findUserById("001");
        entrantList.add(new Entrant(nowuser, scheduledEventRepository.findScheduledEventById(1),
                menuRepository.findMenuById(1), nowuser.getName(), nowuser.getPhone(), nowuser.getAge(), nowuser.isMale(), PaymentMethod.KAKAO,
                ApplicationStatus.ACCEPTED, false, true, null, "재밌어요.", 4.5, Date.valueOf(LocalDate.of(2023, 12, 12))));

        nowuser = userRepository.findUserById("002");
        entrantList.add(new Entrant(nowuser, scheduledEventRepository.findScheduledEventById(1),
                menuRepository.findMenuById(1), nowuser.getName(), nowuser.getPhone(), nowuser.getAge(), nowuser.isMale(), PaymentMethod.CREDIT,
                ApplicationStatus.ACCEPTED, false, true, null, "할만하네요.", 4.1, Date.valueOf(LocalDate.of(2023, 12, 12))));

        nowuser = userRepository.findUserById("003");
        entrantList.add(new Entrant(nowuser, scheduledEventRepository.findScheduledEventById(2),
                menuRepository.findMenuById(1), nowuser.getName(), nowuser.getPhone(), nowuser.getAge(), nowuser.isMale(), PaymentMethod.CASH,
                ApplicationStatus.ACCEPTED, false, true, "현금 사용 불가 이벤트네요...", null, 4.2, null));

        nowuser = userRepository.findUserById("004");
        entrantList.add(new Entrant(nowuser, scheduledEventRepository.findScheduledEventById(3),
                menuRepository.findMenuById(1), nowuser.getName(), nowuser.getPhone(), nowuser.getAge(), nowuser.isMale(), PaymentMethod.KAKAO,
                ApplicationStatus.ACCEPTED, false, true, null, null, 4.4, null));

        nowuser = userRepository.findUserById("005");
        entrantList.add(new Entrant(nowuser, scheduledEventRepository.findScheduledEventById(1),
                menuRepository.findMenuById(1), nowuser.getName(), nowuser.getPhone(), nowuser.getAge(), nowuser.isMale(), PaymentMethod.KAKAO,
                ApplicationStatus.ACCEPTED, false, true, null, "생각보다 어려워요.",
                4.0, Date.valueOf(LocalDate.of(2023, 12, 10))));

        nowuser = userRepository.findUserById("006");
        entrantList.add(new Entrant(nowuser, scheduledEventRepository.findScheduledEventById(1),
                menuRepository.findMenuById(1), nowuser.getName(), nowuser.getPhone(), nowuser.getAge(), nowuser.isMale(), PaymentMethod.KAKAO,
                ApplicationStatus.ACCEPTED, false, true, null, "스타벅스 알바 해보고 싶었는데 재밌었어요.",
                4.8, Date.valueOf(LocalDate.of(2023, 12, 12))));

        nowuser = userRepository.findUserById("005");
        entrantList.add(new Entrant(nowuser, scheduledEventRepository.findScheduledEventById(2),
                menuRepository.findMenuById(1), nowuser.getName(), nowuser.getPhone(), nowuser.getAge(), nowuser.isMale(), PaymentMethod.KAKAO,
                ApplicationStatus.ACCEPTED, false, true, null, "테스트 공간이 생기니까 너무 편해요.",
                4.9, Date.valueOf(LocalDate.of(2023, 12, 10))));

        nowuser = userRepository.findUserById("006");
        entrantList.add(new Entrant(nowuser, scheduledEventRepository.findScheduledEventById(2),
                menuRepository.findMenuById(1), nowuser.getName(), nowuser.getPhone(), nowuser.getAge(), nowuser.isMale(), PaymentMethod.KAKAO,
                ApplicationStatus.ACCEPTED, false, true, null, "공간이 조금 번잡하네요.",
                4.2, Date.valueOf(LocalDate.of(2023, 12, 12))));

        nowuser = userRepository.findUserById("005");
        entrantList.add(new Entrant(nowuser, scheduledEventRepository.findScheduledEventById(3),
                menuRepository.findMenuById(1), nowuser.getName(), nowuser.getPhone(), nowuser.getAge(), nowuser.isMale(), PaymentMethod.KAKAO,
                ApplicationStatus.ACCEPTED, false, true, null, "문제가 너무 어려워요 ㅠㅠ.",
                3.3, Date.valueOf(LocalDate.of(2023, 12, 11))));

        nowuser = userRepository.findUserById("006");
        entrantList.add(new Entrant(nowuser, scheduledEventRepository.findScheduledEventById(3),
                menuRepository.findMenuById(1), nowuser.getName(), nowuser.getPhone(), nowuser.getAge(), nowuser.isMale(), PaymentMethod.KAKAO,
                ApplicationStatus.ACCEPTED, false, true, null, "아싸 상금!!!",
                5.0, Date.valueOf(LocalDate.of(2023, 12, 12))));

        nowuser = userRepository.findUserById("007");
        entrantList.add(new Entrant(nowuser, scheduledEventRepository.findScheduledEventById(1),
                menuRepository.findMenuById(1), nowuser.getName(), nowuser.getPhone(), nowuser.getAge(), nowuser.isMale(), PaymentMethod.KAKAO,
                ApplicationStatus.ACCEPTED, false, true, null, "신기한 경험이었어요.", 4.1, Date.valueOf(LocalDate.of(2023, 12, 12))));

        nowuser = userRepository.findUserById("008");
        entrantList.add(new Entrant(nowuser, scheduledEventRepository.findScheduledEventById(2),
                menuRepository.findMenuById(1), nowuser.getName(), nowuser.getPhone(), nowuser.getAge(), nowuser.isMale(), PaymentMethod.KAKAO,
                ApplicationStatus.ACCEPTED, false, true, null, "최악이에요.", 2.2, Date.valueOf(LocalDate.of(2023, 12, 12))));

        nowuser = userRepository.findUserById("009");
        entrantList.add(new Entrant(nowuser, scheduledEventRepository.findScheduledEventById(2),
                menuRepository.findMenuById(1), nowuser.getName(), nowuser.getPhone(), nowuser.getAge(), nowuser.isMale(), PaymentMethod.KAKAO,
                ApplicationStatus.REJECTED, true, false, "준비물 부족.", null, 0.0, null));

        nowuser = userRepository.findUserById("010");
        entrantList.add(new Entrant(nowuser, scheduledEventRepository.findScheduledEventById(2),
                menuRepository.findMenuById(1), nowuser.getName(), nowuser.getPhone(), nowuser.getAge(), nowuser.isMale(), PaymentMethod.KAKAO,
                ApplicationStatus.PENDING, false, false, null, null, 0.0, null));
        entrantRepository.saveAll(entrantList);
    }

    public void ReservationEntity(){
        List<Reservation> reservationList = new ArrayList<>();

        reservationList.add(new Reservation(userRepository.findUserById("004"), "디비팀플모임", 4, 30000,
                PaymentMethod.CREDIT, false, userRepository.findUserById("004").getName(), userRepository.findUserById("004").getPhone(),
                0, false));
        reservationList.add(new Reservation(userRepository.findUserById("005"), "꽃배달 모임", 8, 80000,
                PaymentMethod.CREDIT, false, userRepository.findUserById("005").getName(), userRepository.findUserById("005").getPhone(),
                0, false));
        reservationList.add(new Reservation(userRepository.findUserById("006"), "아이브 팬카페 정기", 10, 100000,
                PaymentMethod.CREDIT, false, userRepository.findUserById("006").getName(), userRepository.findUserById("006").getPhone(),
                0, false));
        reservationList.add(new Reservation(userRepository.findUserById("002"), "저녁 노가리", 3, 30000,
                PaymentMethod.CREDIT, false, userRepository.findUserById("002").getName(), userRepository.findUserById("002").getPhone(),
                0, false));
        reservationList.add(new Reservation(userRepository.findUserById("001"), "커피 탐구회", 10, 150000,
                PaymentMethod.CREDIT, false, userRepository.findUserById("001").getName(), userRepository.findUserById("001").getPhone(),
                0, false));
        reservationRepository.saveAll(reservationList);
    }

    public void ReservationBlockEntity(){
        List<ReservationBlock> reservationBlockList = new ArrayList<>();

        reservationBlockList.add(new ReservationBlock(placeRepository.findPlaceById(1), LocalDate.of(2023,12,19),
                LocalTime.of(10, 00), LocalTime.of(12, 00), false));
        reservationBlockList.add(new ReservationBlock(placeRepository.findPlaceById(1), LocalDate.of(2023,12,19),
                LocalTime.of(12, 00), LocalTime.of(14, 00), true));
        reservationBlockList.add(new ReservationBlock(placeRepository.findPlaceById(1), LocalDate.of(2023,12,19),
                LocalTime.of(14, 00), LocalTime.of(16, 00), true));
        reservationBlockList.add(new ReservationBlock(placeRepository.findPlaceById(1), LocalDate.of(2023,12,19),
                LocalTime.of(16, 00), LocalTime.of(18, 00), true));
        reservationBlockList.add(new ReservationBlock(placeRepository.findPlaceById(1), LocalDate.of(2023,12,19),
                LocalTime.of(18, 00), LocalTime.of(20, 00), false));

        reservationBlockList.add(new ReservationBlock(placeRepository.findPlaceById(1), LocalDate.of(2023,12,20),
                LocalTime.of(10, 00), LocalTime.of(12, 00), true));
        reservationBlockList.add(new ReservationBlock(placeRepository.findPlaceById(1), LocalDate.of(2023,12,20),
                LocalTime.of(12, 00), LocalTime.of(14, 00), true));
        reservationBlockList.add(new ReservationBlock(placeRepository.findPlaceById(1), LocalDate.of(2023,12,20),
                LocalTime.of(14, 00), LocalTime.of(16, 00), true));
        reservationBlockList.add(new ReservationBlock(placeRepository.findPlaceById(1), LocalDate.of(2023,12,20),
                LocalTime.of(16, 00), LocalTime.of(18, 00), true));
        reservationBlockList.add(new ReservationBlock(placeRepository.findPlaceById(1), LocalDate.of(2023,12,20),
                LocalTime.of(18, 00), LocalTime.of(20, 00), true));

        reservationBlockRepository.saveAll(reservationBlockList);
    }

    public void ReservationChangeRequestEntity(){
        List<ReservationChangeRequest> reservationChangeRequestList = new ArrayList<>();

        reservationChangeRequestList.add(new ReservationChangeRequest(reservationRepository.findReservationById(1), LocalDate.of(23,12,20),
                LocalTime.of(14,00), LocalTime.of(16,00), false, "이미 예약된 시간입니다??"));
        reservationChangeRequestRepository.saveAll(reservationChangeRequestList);
    }

    public void ReservationItemEntity(){
        List<ReservationItem> reservationItemList = new ArrayList<>();

        reservationItemList.add(new ReservationItem(reservationRepository.findReservationById(1), reservationBlockRepository.findReservationBlockById(1), "1111",
                reservationRepository.findReservationById(1).getPrepaymentTotal(), settingRepository.findByName("얼리버드할인율").getValue(),
                settingRepository.findByName("주중할인율").getValue()));

        reservationItemList.add(new ReservationItem(reservationRepository.findReservationById(2), reservationBlockRepository.findReservationBlockById(2), "1111",
                reservationRepository.findReservationById(2).getPrepaymentTotal(), settingRepository.findByName("얼리버드할인율").getValue(),
                settingRepository.findByName("주중할인율").getValue()));

        reservationItemList.add(new ReservationItem(reservationRepository.findReservationById(3), reservationBlockRepository.findReservationBlockById(3), "1111",
                reservationRepository.findReservationById(3).getPrepaymentTotal(), settingRepository.findByName("얼리버드할인율").getValue(),
                settingRepository.findByName("주중할인율").getValue()));
        reservationItemList.add(new ReservationItem(reservationRepository.findReservationById(5), reservationBlockRepository.findReservationBlockById(4), "1111",
                reservationRepository.findReservationById(3).getPrepaymentTotal(), settingRepository.findByName("얼리버드할인율").getValue(),
                settingRepository.findByName("주중할인율").getValue()));

        reservationItemRepository.saveAll(reservationItemList);
    }

    public void CouponEntity(){
        List<Coupon> couponList = new ArrayList<>();

        couponList.add(new Coupon("웰컴쿠폰", 10, 3000, 30, 100));
        couponList.add(new Coupon("오랜만쿠폰", 30, 5000, 30, 100));
        couponList.add(new Coupon("장기고객쿠폰", 15, 5000, 60, 100));
        couponList.add(new Coupon("명절축하쿠폰", 20, 4000, 15, 100));
        couponRepository.saveAll(couponList);
    }

    public void OwnCouponEntity(){
        List<OwnCoupon> ownCouponList = new ArrayList<>();

        ownCouponList.add(new OwnCoupon(couponRepository.findCouponById(1), userRepository.findUserById("003"), CouponStatus.USABLE));
        ownCouponList.add(new OwnCoupon(couponRepository.findCouponById(2), userRepository.findUserById("002"), CouponStatus.USABLE));
        ownCouponList.add(new OwnCoupon(couponRepository.findCouponById(3), userRepository.findUserById("004"), CouponStatus.USABLE));
        ownCouponList.add(new OwnCoupon(couponRepository.findCouponById(4), userRepository.findUserById("005"), CouponStatus.USABLE));

        ownCouponRepository.saveAll(ownCouponList);
    }

    public void LevelHistoryEntity(){
        List<LevelHistory> levelHistoryList = new ArrayList<>();

        levelHistoryList.add(new LevelHistory(userRepository.findUserById("001"), 0, Level.BRONZE, 2023, 10));
        levelHistoryList.add(new LevelHistory(userRepository.findUserById("002"), 0, Level.DIAMOND, 2023, 10));
        levelHistoryList.add(new LevelHistory(userRepository.findUserById("003"), 0, Level.GOLD, 2023, 10));
        levelHistoryList.add(new LevelHistory(userRepository.findUserById("004"), 0, Level.SILVER, 2023, 10));
        levelHistoryList.add(new LevelHistory(userRepository.findUserById("005"), 0, Level.SILVER, 2023, 10));
        levelHistoryList.add(new LevelHistory(userRepository.findUserById("006"), 0, Level.SILVER, 2023, 10));

        levelHistoryList.add(new LevelHistory(userRepository.findUserById("001"), 0, Level.BRONZE, 2023, 11));
        levelHistoryList.add(new LevelHistory(userRepository.findUserById("002"), 0, Level.DIAMOND, 2023, 11));
        levelHistoryList.add(new LevelHistory(userRepository.findUserById("003"), 0, Level.GOLD, 2023, 11));
        levelHistoryList.add(new LevelHistory(userRepository.findUserById("004"), 0, Level.SILVER, 2023, 11));
        levelHistoryList.add(new LevelHistory(userRepository.findUserById("005"), 0, Level.SILVER, 2023, 11));
        levelHistoryList.add(new LevelHistory(userRepository.findUserById("006"), 0, Level.SILVER, 2023, 11));

        levelHistoryRepository.saveAll(levelHistoryList);
    }

    public void MileageHistoryEntity(){
        List<MileageHistory> mileageHistoryList = new ArrayList<>();

        mileageHistoryList.add(new MileageHistory(userRepository.findUserById("001"), true, 700, "내용없음"));
        mileageHistoryList.add(new MileageHistory(userRepository.findUserById("002"), true, 500, "내용없음"));

        mileageHistoryRepository.saveAll(mileageHistoryList);
    }

    public void PrizeHistoryEntity(){
        List<PrizeHistory> prizeHistoryList = new ArrayList<>();

        prizeHistoryList.add(new PrizeHistory(userRepository.findUserById("002"), prizeRepository.findPrizeById(4)));
        prizeHistoryList.add(new PrizeHistory(userRepository.findUserById("003"), prizeRepository.findPrizeById(3)));
        prizeHistoryList.add(new PrizeHistory(userRepository.findUserById("004"), prizeRepository.findPrizeById(3)));
        prizeHistoryList.add(new PrizeHistory(userRepository.findUserById("005"), prizeRepository.findPrizeById(3)));
        prizeHistoryList.add(new PrizeHistory(userRepository.findUserById("005"), prizeRepository.findPrizeById(3)));

        prizeHistoryRepository.saveAll(prizeHistoryList);
    }

    public void PrizeEntity(){
        List<Prize> prizeList = new ArrayList<>();

        prizeList.add(new Prize("300포인트", 300, 0, 10));
        prizeList.add(new Prize("100포인트", 100, 0, 40));
        prizeList.add(new Prize("50포인트", 50, 0, 50));
        prizeList.add(new Prize("2코인", 0, 2, 5));
        prizeList.add(new Prize("5코인", 0, 5, 1));

        prizeRepository.saveAll(prizeList);
    }

    public void SuggestionEntity(){
        List<Suggestion> suggestionList = new ArrayList<>();

        suggestionList.add(new Suggestion(userRepository.findUserById("001"), "독서모임요청", "독서 모임 요청합니다.", SuggestionCategory.EVENT, "불만 사항 숙지 후, 개선 요청을 반영하도록 하겠습니다."));
        suggestionList.add(new Suggestion(userRepository.findUserById("002"), "홈페이지 수정 요청", "홈페이지가 너무 눈아파요.", SuggestionCategory.WEB, "속히 UI 변경에 반영토록 하겠습니다."));
        suggestionList.add(new Suggestion(userRepository.findUserById("003"), "메뉴 가격", "쌍화탕이 너무 비싸요", SuggestionCategory.CAFE, "쌍화탕 가격에는 약재 가격이 다수 포함되어 있는점 양해바랍니다."));

        suggestionRepository.saveAll(suggestionList);
    }

    public void VoucherEntity(){ // 교환권
        List<Voucher> voucherList = new ArrayList<>();

        voucherList.add(new Voucher(userRepository.findUserById("001"), menuRepository.findMenuById(4), menuRepository.findMenuById(4).getPrice(), Date.valueOf(LocalDate.now()), 30, null, CouponStatus.USABLE));
        voucherList.add(new Voucher(userRepository.findUserById("001"), menuRepository.findMenuById(3), menuRepository.findMenuById(3).getPrice(), Date.valueOf(LocalDate.now()), 30, null, CouponStatus.USED));
        voucherList.add(new Voucher(userRepository.findUserById("002"), menuRepository.findMenuById(2), menuRepository.findMenuById(2).getPrice(), Date.valueOf(LocalDate.now()), 30, null, CouponStatus.USABLE));
        voucherList.add(new Voucher(userRepository.findUserById("003"), menuRepository.findMenuById(4), menuRepository.findMenuById(4).getPrice(), Date.valueOf(LocalDate.now()), 30, null, CouponStatus.USABLE));
        voucherList.add(new Voucher(userRepository.findUserById("004"), menuRepository.findMenuById(4), menuRepository.findMenuById(4).getPrice(), Date.valueOf(LocalDate.now()), 30, null, CouponStatus.USABLE));
        voucherList.add(new Voucher(userRepository.findUserById("005"), menuRepository.findMenuById(4), menuRepository.findMenuById(4).getPrice(), Date.valueOf(LocalDate.now()), 30, null, CouponStatus.USABLE));

        voucherRepository.saveAll(voucherList);
    }

}
