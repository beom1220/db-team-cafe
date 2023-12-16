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

        userRepository.save(new User("kimDongSeo", null, "1111", "김동서", "010-2023-1218", 24, true, 700, 20000000, 54, 5, Level.DIAMOND));
        //userList.add(new User("001", null, "1111", "신민섭", "010-4543-6156", 24, true, 0, 0, 0, 5, Level.BRONZE));
        userList.add(new User("mrLow", userRepository.findUserById("kimDongSeo"), "1111", "박소웨", "010-1111-2222", 24, true, 500, 20000, 50, 5, Level.DIAMOND));
        userList.add(new User("kimmin1", userRepository.findUserById("kimDongSeo"), "1111", "이부산", "010-1234-5678", 24, false, 50000, 300000, 30, 5, Level.GOLD));
        userList.add(new User("beom1220", userRepository.findUserById("kimDongSeo"), "1111", "최한국", "010-2333-4444", 24, true, 2000, 100000, 10, 5, Level.SILVER));
        userList.add(new User("tttgood", userRepository.findUserById("kimDongSeo"), "1111", "김정현", "010-1199-2222", 24, true, 3000, 90000, 9, 5, Level.SILVER));
        userList.add(new User("dkanrjsk010", userRepository.findUserById("kimDongSeo"), "1111", "신진성", "010-4455-1122", 24, true, 4000, 80000, 8, 5, Level.SILVER));

        userList.add(new User("dlwlrma", userRepository.findUserById("kimDongSeo"), "1111", "수한무", "010-1111-1122", 24, true, 2000, 250000, 25, 5, Level.GOLD));
        userList.add(new User("akak8282", userRepository.findUserById("kimDongSeo"), "1111", "거북이", "010-4632-1122", 24, true, 1000, 1270000, 27, 5, Level.GOLD));
        userList.add(new User("dndndn23", userRepository.findUserById("kimDongSeo"), "1111", "두루미", "010-5326-1122", 24, true, 1000, 320000, 32, 5, Level.GOLD));
        userList.add(new User("alliswell", userRepository.findUserById("kimDongSeo"), "1111", "동방삭", "010-2754-1122", 24, true, 1000, 700000, 70, 5, Level.DIAMOND));
        userList.add(new User("coffeemaster", userRepository.findUserById("kimDongSeo"), "1111", "토끼", "010-5678-1122", 24, true, 1000, 560000, 2, 5, Level.BRONZE));
        userList.add(new User("vlrhsgkek", userRepository.findUserById("kimDongSeo"), "1111", "자라", "010-4685-1122", 24, true, 1000, 500000, 5, 5, Level.SILVER));
        userList.add(new User("wlqdprkrhtlvek", userRepository.findUserById("kimDongSeo"), "1111", "도라에몽", "010-4685-1122", 24, true, 1000, 350000, 3, 5, Level.BRONZE));
        userList.add(new User("rmaksgkffo", userRepository.findUserById("kimDongSeo"), "1111", "노진구", "010-4685-1122", 24, true, 1000, 800000, 8, 5, Level.DIAMOND));
        userList.add(new User("arirang", userRepository.findUserById("kimDongSeo"), "1111", "짱구", "010-4685-1122", 24, true, 1000, 3000000, 300, 5, Level.VIP));

        userRepository.saveAll(userList);
    }

    public void ScheduledEventEntity(){
        List<ScheduledEvent> scheduledEventList = new ArrayList<>();

        scheduledEventList.add(new ScheduledEvent(eventRepository.findEventById(1), placeRepository.findPlaceById(1),
                LocalDate.of(2024, 1, 2), LocalTime.of(12,0), LocalTime.of(14,0),
                false, Tag.BEST));
        scheduledEventList.add(new ScheduledEvent(eventRepository.findEventById(2), placeRepository.findPlaceById(2),
                LocalDate.of(2024, 1, 15), LocalTime.of(14,0), LocalTime.of(16,0),
                false, Tag.NEW));
        scheduledEventList.add(new ScheduledEvent(eventRepository.findEventById(3), placeRepository.findPlaceById(3),
                LocalDate.of(2024, 2, 5), LocalTime.of(12,0), LocalTime.of(14,0),
                false, Tag.NOTHING));
        scheduledEventList.add(new ScheduledEvent(eventRepository.findEventById(4), placeRepository.findPlaceById(3),
                LocalDate.of(2024, 2, 7), LocalTime.of(18,0), LocalTime.of(20,0),
                false, Tag.NOTHING));

        scheduledEventList.add(new ScheduledEvent(eventRepository.findEventById(5), placeRepository.findPlaceById(3),
                LocalDate.of(2024, 2, 8), LocalTime.of(18,0), LocalTime.of(20,0),
                true, Tag.NOTHING));
        scheduledEventList.add(new ScheduledEvent(eventRepository.findEventById(5), placeRepository.findPlaceById(3),
                LocalDate.of(2024, 2, 9), LocalTime.of(18,0), LocalTime.of(20,0),
                true, Tag.NOTHING));
        scheduledEventList.add(new ScheduledEvent(eventRepository.findEventById(5), placeRepository.findPlaceById(3),
                LocalDate.of(2024, 2, 10), LocalTime.of(18,0), LocalTime.of(20,0),
                true, Tag.NOTHING));

        scheduledEventRepository.saveAll(scheduledEventList);
    }

    public void EventEntity(){
        List<Event> eventList = new ArrayList<>();

        eventList.add(new Event("바리스타 체험", "직접 바리스타가 되어보세요.", true, 10, 15000, "사진", 5, 21.5));
        eventList.add(new Event("신메뉴 테스트", "생각중인 신메뉴를 테스트할 기회!", true, 10, 30000, "사진", 4, 15.3));
        eventList.add(new Event("독서 골든벨", "책을 많이 읽었다면 도전하세요.", true, 20, 3000, "사진", 3, 12.7));
        eventList.add(new Event("천하제일 자랑대회", "당신의 굿즈를 자랑해보세요", false, 10, 1500, "사진", 0, 0));
        eventList.add(new Event("제빵교실", "디저트를 사랑하는 당신에게", false, 7, 4000, "사진", 24, 110));
        eventList.add(new Event("알고리즘 풀이 모임", "세상이 0과 1로 보여요", false, 10, 5000, "사진", 0, 0));
        eventList.add(new Event("제빵교실", "디저트를 사랑하는 당신에게", false, 7, 4000, "사진", 24, 110));
        eventList.add(new Event("제빵교실", "디저트를 사랑하는 당신에게", false, 7, 4000, "사진", 24, 110));


        eventRepository.saveAll(eventList);
    }

    public void PlaceEntity(){
        List<Place> placeList = new ArrayList<>();

        placeList.add(new Place(10, true, true, 8, false));
        placeList.add(new Place(10, true, true, 6, true));
        placeList.add(new Place(10, true, true, 4, false));
        placeList.add(new Place(10, true, true, 4, true));
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
        settingList.add(new Setting(16, "브론즈기준", "", 100));
        settingList.add(new Setting(17, "실버기준", "", 80));
        settingList.add(new Setting(18, "골드기준", "", 60));
        settingList.add(new Setting(19, "다이아기준", "", 30));
        settingList.add(new Setting(20, "VIP기준", "", 0));

        settingRepository.saveAll(settingList);
    }

    public void OrdersEntity(){
        List<Orders> ordersList = new ArrayList<>();
        ordersList.add(new Orders(userRepository.findUserById("kimDongSeo"), PaymentMethod.KAKAO, 10000, OrderStatus.COMPLETE,
                false, 4000, settingRepository.findByName("주중할인율").getValue(),
                500, settingRepository.findByName("브론즈할인율").getValue(), 300, 0, 5200));
        ordersList.add(new Orders(userRepository.findUserById("mrLow"), PaymentMethod.CREDIT, 30000, OrderStatus.PREPARING,
                false, 0, settingRepository.findByName("주중할인율").getValue(),
                1500, 20, 6000, 0, 22500));
        ordersList.add(new Orders(userRepository.findUserById("kimmin1"), PaymentMethod.CASH, 8000, OrderStatus.CANCELING,
                false, 0, settingRepository.findByName("주중할인율").getValue(),
                400, 10, 800, 0, 5500));
        ordersList.add(new Orders(userRepository.findUserById("beom1220"), PaymentMethod.KAKAO, 15000, OrderStatus.PREPARING,
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

        cartList.add(new Cart(userRepository.findUserById("kimDongSeo")));
        cartList.add(new Cart(userRepository.findUserById("mrLow")));
        cartList.add(new Cart(userRepository.findUserById("kimmin1")));
        cartList.add(new Cart(userRepository.findUserById("beom1220")));
        cartList.add(new Cart(userRepository.findUserById("tttgood")));
        cartList.add(new Cart(userRepository.findUserById("dkanrjsk010")));

        cartRepository.saveAll(cartList);
    }

    public void CartItemEntity(){
        List<CartItem> cartItemList = new ArrayList<>();

        cartItemList.add(new CartItem(cartRepository.findByUser(userRepository.findUserById("kimDongSeo")), menuRepository.findMenuById(1), 4));
        cartItemList.add(new CartItem(cartRepository.findByUser(userRepository.findUserById("kimDongSeo")), menuRepository.findMenuById(2), 2));
        cartItemList.add(new CartItem(cartRepository.findByUser(userRepository.findUserById("kimDongSeo")), menuRepository.findMenuById(3), 5));
        cartItemList.add(new CartItem(cartRepository.findByUser(userRepository.findUserById("kimDongSeo")), menuRepository.findMenuById(4), 2));
        cartItemList.add(new CartItem(cartRepository.findByUser(userRepository.findUserById("kimDongSeo")), menuRepository.findMenuById(5), 5));
        cartItemList.add(new CartItem(cartRepository.findByUser(userRepository.findUserById("kimDongSeo")), menuRepository.findMenuById(2), 2));
        cartItemList.add(new CartItem(cartRepository.findByUser(userRepository.findUserById("mrLow")), menuRepository.findMenuById(1), 4));
        cartItemList.add(new CartItem(cartRepository.findByUser(userRepository.findUserById("mrLow")), menuRepository.findMenuById(3), 3));
        cartItemList.add(new CartItem(cartRepository.findByUser(userRepository.findUserById("kimmin1")), menuRepository.findMenuById(5), 1));
        cartItemList.add(new CartItem(cartRepository.findByUser(userRepository.findUserById("beom1220")), menuRepository.findMenuById(2), 2));
        cartItemList.add(new CartItem(cartRepository.findByUser(userRepository.findUserById("beom1220")), menuRepository.findMenuById(1), 6));
        cartItemList.add(new CartItem(cartRepository.findByUser(userRepository.findUserById("tttgood")), menuRepository.findMenuById(4), 4));
        cartItemList.add(new CartItem(cartRepository.findByUser(userRepository.findUserById("dkanrjsk010")), menuRepository.findMenuById(5), 2));
        cartItemList.add(new CartItem(cartRepository.findByUser(userRepository.findUserById("dlwlrma")), menuRepository.findMenuById(5), 2));
        cartItemList.add(new CartItem(cartRepository.findByUser(userRepository.findUserById("akak8282")), menuRepository.findMenuById(5), 2));
        cartItemList.add(new CartItem(cartRepository.findByUser(userRepository.findUserById("dndndn23")), menuRepository.findMenuById(5), 2));
        cartItemList.add(new CartItem(cartRepository.findByUser(userRepository.findUserById("alliswell")), menuRepository.findMenuById(5), 2));

        cartItemRepository.saveAll(cartItemList);
    }

    public void EntrantEntity(){
        List<Entrant> entrantList = new ArrayList<>();
        User nowuser = userRepository.findUserById("kimDongSeo");
        entrantList.add(new Entrant(nowuser, scheduledEventRepository.findScheduledEventById(1),
                menuRepository.findMenuById(1), nowuser.getName(), nowuser.getPhone(), nowuser.getAge(), nowuser.isMale(), PaymentMethod.KAKAO,
                ApplicationStatus.PENDING, false, true, null, "재밌어요.", 4.5, Date.valueOf(LocalDate.of(2023, 12, 12))));

        nowuser = userRepository.findUserById("mrLow");
        entrantList.add(new Entrant(nowuser, scheduledEventRepository.findScheduledEventById(1),
                menuRepository.findMenuById(1), nowuser.getName(), nowuser.getPhone(), nowuser.getAge(), nowuser.isMale(), PaymentMethod.CREDIT,
                ApplicationStatus.PENDING, false, true, null, "할만하네요.", 4.1, Date.valueOf(LocalDate.of(2023, 12, 12))));

        nowuser = userRepository.findUserById("kimmin1");
        entrantList.add(new Entrant(nowuser, scheduledEventRepository.findScheduledEventById(2),
                menuRepository.findMenuById(1), nowuser.getName(), nowuser.getPhone(), nowuser.getAge(), nowuser.isMale(), PaymentMethod.CASH,
                ApplicationStatus.PENDING, false, true, "현금 사용 불가 이벤트네요...", null, 4.2, null));

        nowuser = userRepository.findUserById("beom1220");
        entrantList.add(new Entrant(nowuser, scheduledEventRepository.findScheduledEventById(3),
                menuRepository.findMenuById(1), nowuser.getName(), nowuser.getPhone(), nowuser.getAge(), nowuser.isMale(), PaymentMethod.KAKAO,
                ApplicationStatus.PENDING, false, true, null, null, 4.4, null));

        nowuser = userRepository.findUserById("tttgood");
        entrantList.add(new Entrant(nowuser, scheduledEventRepository.findScheduledEventById(1),
                menuRepository.findMenuById(1), nowuser.getName(), nowuser.getPhone(), nowuser.getAge(), nowuser.isMale(), PaymentMethod.KAKAO,
                ApplicationStatus.PENDING, false, true, null, "생각보다 어려워요.",
                4.0, Date.valueOf(LocalDate.of(2023, 12, 10))));

        nowuser = userRepository.findUserById("dkanrjsk010");
        entrantList.add(new Entrant(nowuser, scheduledEventRepository.findScheduledEventById(1),
                menuRepository.findMenuById(1), nowuser.getName(), nowuser.getPhone(), nowuser.getAge(), nowuser.isMale(), PaymentMethod.KAKAO,
                ApplicationStatus.PENDING, false, true, null, "스타벅스 알바 해보고 싶었는데 재밌었어요.",
                4.8, Date.valueOf(LocalDate.of(2023, 12, 12))));

        nowuser = userRepository.findUserById("tttgood");
        entrantList.add(new Entrant(nowuser, scheduledEventRepository.findScheduledEventById(2),
                menuRepository.findMenuById(1), nowuser.getName(), nowuser.getPhone(), nowuser.getAge(), nowuser.isMale(), PaymentMethod.KAKAO,
                ApplicationStatus.PENDING, false, true, null, "테스트 공간이 생기니까 너무 편해요.",
                4.9, Date.valueOf(LocalDate.of(2023, 12, 10))));

        nowuser = userRepository.findUserById("dkanrjsk010");
        entrantList.add(new Entrant(nowuser, scheduledEventRepository.findScheduledEventById(2),
                menuRepository.findMenuById(1), nowuser.getName(), nowuser.getPhone(), nowuser.getAge(), nowuser.isMale(), PaymentMethod.KAKAO,
                ApplicationStatus.PENDING, false, true, null, "공간이 조금 번잡하네요.",
                4.2, Date.valueOf(LocalDate.of(2023, 12, 12))));

        nowuser = userRepository.findUserById("tttgood");
        entrantList.add(new Entrant(nowuser, scheduledEventRepository.findScheduledEventById(3),
                menuRepository.findMenuById(1), nowuser.getName(), nowuser.getPhone(), nowuser.getAge(), nowuser.isMale(), PaymentMethod.KAKAO,
                ApplicationStatus.PENDING, false, true, null, "문제가 너무 어려워요 ㅠㅠ.",
                3.3, Date.valueOf(LocalDate.of(2023, 12, 11))));

        nowuser = userRepository.findUserById("dkanrjsk010");
        entrantList.add(new Entrant(nowuser, scheduledEventRepository.findScheduledEventById(3),
                menuRepository.findMenuById(1), nowuser.getName(), nowuser.getPhone(), nowuser.getAge(), nowuser.isMale(), PaymentMethod.KAKAO,
                ApplicationStatus.ACCEPTED, false, true, null, "아싸 상금!!!",
                5.0, Date.valueOf(LocalDate.of(2023, 12, 12))));

        nowuser = userRepository.findUserById("dlwlrma");
        entrantList.add(new Entrant(nowuser, scheduledEventRepository.findScheduledEventById(1),
                menuRepository.findMenuById(1), nowuser.getName(), nowuser.getPhone(), nowuser.getAge(), nowuser.isMale(), PaymentMethod.KAKAO,
                ApplicationStatus.ACCEPTED, false, true, null, "신기한 경험이었어요.", 4.1, Date.valueOf(LocalDate.of(2023, 12, 12))));

        nowuser = userRepository.findUserById("akak8282");
        entrantList.add(new Entrant(nowuser, scheduledEventRepository.findScheduledEventById(2),
                menuRepository.findMenuById(1), nowuser.getName(), nowuser.getPhone(), nowuser.getAge(), nowuser.isMale(), PaymentMethod.KAKAO,
                ApplicationStatus.PENDING, false, true, null, "최악이에요.", 2.2, Date.valueOf(LocalDate.of(2023, 12, 12))));

        nowuser = userRepository.findUserById("tttgood");
        entrantList.add(new Entrant(nowuser, scheduledEventRepository.findScheduledEventById(5),
                menuRepository.findMenuById(1), nowuser.getName(), nowuser.getPhone(), nowuser.getAge(), nowuser.isMale(), PaymentMethod.KAKAO,
                ApplicationStatus.REJECTED, true, false, "준비물 부족.", null, 0.0, null));
        // 여기서 부터야 민우야
        nowuser = userRepository.findUserById("akak8282");
        entrantList.add(new Entrant(nowuser, scheduledEventRepository.findScheduledEventById(5),
                menuRepository.findMenuById(1), nowuser.getName(), nowuser.getPhone(), nowuser.getAge(), nowuser.isMale(), PaymentMethod.KAKAO,
                ApplicationStatus.PENDING, false, true, null, "할만 하네요.", 4.2, Date.valueOf(LocalDate.of(2023, 12, 12))));

        nowuser = userRepository.findUserById("dkanrjsk010");
        entrantList.add(new Entrant(nowuser, scheduledEventRepository.findScheduledEventById(5),
                menuRepository.findMenuById(1), nowuser.getName(), nowuser.getPhone(), nowuser.getAge(), nowuser.isMale(), PaymentMethod.KAKAO,
                ApplicationStatus.PENDING, false, true, null, "5점이요.", 5.0, Date.valueOf(LocalDate.of(2023, 12, 12))));

        nowuser = userRepository.findUserById("alliswell");
        entrantList.add(new Entrant(nowuser, scheduledEventRepository.findScheduledEventById(5),
                menuRepository.findMenuById(1), nowuser.getName(), nowuser.getPhone(), nowuser.getAge(), nowuser.isMale(), PaymentMethod.KAKAO,
                ApplicationStatus.PENDING, false, true, null, "행복한 하루가 됐습니다.", 5.0, Date.valueOf(LocalDate.of(2023, 12, 12))));

        nowuser = userRepository.findUserById("arirang");
        entrantList.add(new Entrant(nowuser, scheduledEventRepository.findScheduledEventById(5),
                menuRepository.findMenuById(1), nowuser.getName(), nowuser.getPhone(), nowuser.getAge(), nowuser.isMale(), PaymentMethod.KAKAO,
                ApplicationStatus.PENDING, false, true, null, "접시 어디 거에요?.", 4.0, Date.valueOf(LocalDate.of(2023, 12, 12))));

        nowuser = userRepository.findUserById("coffeemaster");
        entrantList.add(new Entrant(nowuser, scheduledEventRepository.findScheduledEventById(5),
                menuRepository.findMenuById(1), nowuser.getName(), nowuser.getPhone(), nowuser.getAge(), nowuser.isMale(), PaymentMethod.KAKAO,
                ApplicationStatus.PENDING, false, true, null, "노래가 좋아요.", 3.8, Date.valueOf(LocalDate.of(2023, 12, 12))));

        nowuser = userRepository.findUserById("dlwlrma");
        entrantList.add(new Entrant(nowuser, scheduledEventRepository.findScheduledEventById(5),
                menuRepository.findMenuById(1), nowuser.getName(), nowuser.getPhone(), nowuser.getAge(), nowuser.isMale(), PaymentMethod.KAKAO,
                ApplicationStatus.PENDING, false, true, null, "점주님 잘생겼어요.", 5.0, Date.valueOf(LocalDate.of(2023, 12, 12))));

        nowuser = userRepository.findUserById("dndndn23");
        entrantList.add(new Entrant(nowuser, scheduledEventRepository.findScheduledEventById(5),
                menuRepository.findMenuById(1), nowuser.getName(), nowuser.getPhone(), nowuser.getAge(), nowuser.isMale(), PaymentMethod.KAKAO,
                ApplicationStatus.PENDING, false, true, null, "다시 또 올게요.", 4.5, Date.valueOf(LocalDate.of(2023, 12, 12))));

        nowuser = userRepository.findUserById("wlqdprkrhtlvek");
        entrantList.add(new Entrant(nowuser, scheduledEventRepository.findScheduledEventById(5),
                menuRepository.findMenuById(1), nowuser.getName(), nowuser.getPhone(), nowuser.getAge(), nowuser.isMale(), PaymentMethod.KAKAO,
                ApplicationStatus.PENDING, false, true, null, "맛있었습니다.", 4.7, Date.valueOf(LocalDate.of(2023, 12, 12))));

        nowuser = userRepository.findUserById("akak8282");
        entrantList.add(new Entrant(nowuser, scheduledEventRepository.findScheduledEventById(7),
                menuRepository.findMenuById(1), nowuser.getName(), nowuser.getPhone(), nowuser.getAge(), nowuser.isMale(), PaymentMethod.KAKAO,
                ApplicationStatus.PENDING, false, true, null, "할만 하네요.", 4.2, Date.valueOf(LocalDate.of(2023, 12, 12))));

        nowuser = userRepository.findUserById("dkanrjsk010");
        entrantList.add(new Entrant(nowuser, scheduledEventRepository.findScheduledEventById(7),
                menuRepository.findMenuById(1), nowuser.getName(), nowuser.getPhone(), nowuser.getAge(), nowuser.isMale(), PaymentMethod.KAKAO,
                ApplicationStatus.PENDING, false, true, null, "5점이요.", 5.0, Date.valueOf(LocalDate.of(2023, 12, 12))));

        nowuser = userRepository.findUserById("alliswell");
        entrantList.add(new Entrant(nowuser, scheduledEventRepository.findScheduledEventById(7),
                menuRepository.findMenuById(1), nowuser.getName(), nowuser.getPhone(), nowuser.getAge(), nowuser.isMale(), PaymentMethod.KAKAO,
                ApplicationStatus.PENDING, false, true, null, "행복한 하루가 됐습니다.", 5.0, Date.valueOf(LocalDate.of(2023, 12, 12))));

        nowuser = userRepository.findUserById("arirang");
        entrantList.add(new Entrant(nowuser, scheduledEventRepository.findScheduledEventById(7),
                menuRepository.findMenuById(1), nowuser.getName(), nowuser.getPhone(), nowuser.getAge(), nowuser.isMale(), PaymentMethod.KAKAO,
                ApplicationStatus.PENDING, false, true, null, "접시 어디 거에요?.", 4.0, Date.valueOf(LocalDate.of(2023, 12, 12))));

        nowuser = userRepository.findUserById("coffeemaster");
        entrantList.add(new Entrant(nowuser, scheduledEventRepository.findScheduledEventById(7),
                menuRepository.findMenuById(1), nowuser.getName(), nowuser.getPhone(), nowuser.getAge(), nowuser.isMale(), PaymentMethod.KAKAO,
                ApplicationStatus.PENDING, false, true, null, "노래가 좋아요.", 3.8, Date.valueOf(LocalDate.of(2023, 12, 12))));

        nowuser = userRepository.findUserById("dlwlrma");
        entrantList.add(new Entrant(nowuser, scheduledEventRepository.findScheduledEventById(7),
                menuRepository.findMenuById(1), nowuser.getName(), nowuser.getPhone(), nowuser.getAge(), nowuser.isMale(), PaymentMethod.KAKAO,
                ApplicationStatus.PENDING, false, true, null, "점주님 잘생겼어요.", 5.0, Date.valueOf(LocalDate.of(2023, 12, 12))));

        nowuser = userRepository.findUserById("dndndn23");
        entrantList.add(new Entrant(nowuser, scheduledEventRepository.findScheduledEventById(7),
                menuRepository.findMenuById(1), nowuser.getName(), nowuser.getPhone(), nowuser.getAge(), nowuser.isMale(), PaymentMethod.KAKAO,
                ApplicationStatus.PENDING, false, true, null, "다시 또 올게요.", 4.5, Date.valueOf(LocalDate.of(2023, 12, 12))));

        nowuser = userRepository.findUserById("wlqdprkrhtlvek");
        entrantList.add(new Entrant(nowuser, scheduledEventRepository.findScheduledEventById(7),
                menuRepository.findMenuById(1), nowuser.getName(), nowuser.getPhone(), nowuser.getAge(), nowuser.isMale(), PaymentMethod.KAKAO,
                ApplicationStatus.PENDING, false, true, null, "맛있었습니다.", 4.7, Date.valueOf(LocalDate.of(2023, 12, 12))));

        nowuser = userRepository.findUserById("akak8282");
        entrantList.add(new Entrant(nowuser, scheduledEventRepository.findScheduledEventById(6),
                menuRepository.findMenuById(1), nowuser.getName(), nowuser.getPhone(), nowuser.getAge(), nowuser.isMale(), PaymentMethod.KAKAO,
                ApplicationStatus.PENDING, false, true, null, "할만 하네요.", 4.2, Date.valueOf(LocalDate.of(2023, 12, 12))));

        nowuser = userRepository.findUserById("dkanrjsk010");
        entrantList.add(new Entrant(nowuser, scheduledEventRepository.findScheduledEventById(6),
                menuRepository.findMenuById(1), nowuser.getName(), nowuser.getPhone(), nowuser.getAge(), nowuser.isMale(), PaymentMethod.KAKAO,
                ApplicationStatus.PENDING, false, true, null, "5점이요.", 5.0, Date.valueOf(LocalDate.of(2023, 12, 12))));

        nowuser = userRepository.findUserById("alliswell");
        entrantList.add(new Entrant(nowuser, scheduledEventRepository.findScheduledEventById(6),
                menuRepository.findMenuById(1), nowuser.getName(), nowuser.getPhone(), nowuser.getAge(), nowuser.isMale(), PaymentMethod.KAKAO,
                ApplicationStatus.PENDING, false, true, null, "행복한 하루가 됐습니다.", 5.0, Date.valueOf(LocalDate.of(2023, 12, 12))));

        nowuser = userRepository.findUserById("arirang");
        entrantList.add(new Entrant(nowuser, scheduledEventRepository.findScheduledEventById(6),
                menuRepository.findMenuById(1), nowuser.getName(), nowuser.getPhone(), nowuser.getAge(), nowuser.isMale(), PaymentMethod.KAKAO,
                ApplicationStatus.PENDING, false, true, null, "접시 어디 거에요?.", 4.0, Date.valueOf(LocalDate.of(2023, 12, 12))));

        nowuser = userRepository.findUserById("coffeemaster");
        entrantList.add(new Entrant(nowuser, scheduledEventRepository.findScheduledEventById(6),
                menuRepository.findMenuById(1), nowuser.getName(), nowuser.getPhone(), nowuser.getAge(), nowuser.isMale(), PaymentMethod.KAKAO,
                ApplicationStatus.PENDING, false, true, null, "노래가 좋아요.", 3.8, Date.valueOf(LocalDate.of(2023, 12, 12))));

        nowuser = userRepository.findUserById("dlwlrma");
        entrantList.add(new Entrant(nowuser, scheduledEventRepository.findScheduledEventById(6),
                menuRepository.findMenuById(1), nowuser.getName(), nowuser.getPhone(), nowuser.getAge(), nowuser.isMale(), PaymentMethod.KAKAO,
                ApplicationStatus.PENDING, false, true, null, "점주님 잘생겼어요.", 5.0, Date.valueOf(LocalDate.of(2023, 12, 12))));

        nowuser = userRepository.findUserById("dndndn23");
        entrantList.add(new Entrant(nowuser, scheduledEventRepository.findScheduledEventById(6),
                menuRepository.findMenuById(1), nowuser.getName(), nowuser.getPhone(), nowuser.getAge(), nowuser.isMale(), PaymentMethod.KAKAO,
                ApplicationStatus.PENDING, false, true, null, "다시 또 올게요.", 4.5, Date.valueOf(LocalDate.of(2023, 12, 12))));

        nowuser = userRepository.findUserById("wlqdprkrhtlvek");
        entrantList.add(new Entrant(nowuser, scheduledEventRepository.findScheduledEventById(6),
                menuRepository.findMenuById(1), nowuser.getName(), nowuser.getPhone(), nowuser.getAge(), nowuser.isMale(), PaymentMethod.KAKAO,
                ApplicationStatus.PENDING, false, true, null, "맛있었습니다.", 4.7, Date.valueOf(LocalDate.of(2023, 12, 12))));

        java.util.Date now = new java.util.Date();
        nowuser = userRepository.findUserById("kimmin1");
        entrantList.add(new Entrant(nowuser, scheduledEventRepository.findScheduledEventById(2),
                menuRepository.findMenuById(1), nowuser.getName(), nowuser.getPhone(), nowuser.getAge(), nowuser.isMale(), PaymentMethod.KAKAO,
                ApplicationStatus.PENDING, false, false, null, null, 0.0, now));

        nowuser = userRepository.findUserById("rmaksgkffo");
        entrantList.add(new Entrant(nowuser, scheduledEventRepository.findScheduledEventById(2),
                menuRepository.findMenuById(1), nowuser.getName(), nowuser.getPhone(), nowuser.getAge(), nowuser.isMale(), PaymentMethod.KAKAO,
                ApplicationStatus.REJECTED, true, false, "준비물 부족.", null, 0.0, null));

        String[] userIds = {"akak8282", "dndndn23", "alliswell", "coffeemaster", "vlrhsgkek", "wlqdprkrhtlvek", "rmaksgkffo", "dlwlrma"};
        String[] reviews = {"매우 만족합니다!", "최고예요!", "다시 참여하고 싶어요!"};
        double[] ratings = {4.8, 4.9, 5.0};

//        nowuser = userRepository.findUserById("dlwlrma");
//        entrantList.add(new Entrant(nowuser, scheduledEventRepository.findScheduledEventById(5),
//                menuRepository.findMenuById(1), nowuser.getName(), nowuser.getPhone(), nowuser.getAge(), nowuser.isMale(), PaymentMethod.KAKAO,
//                ApplicationStatus.PENDING, false, true, null, "맘에 드네요", 5.0, now));
//
//        nowuser = userRepository.findUserById("dndndn23");
//        entrantList.add(new Entrant(nowuser, scheduledEventRepository.findScheduledEventById(6),
//                menuRepository.findMenuById(1), nowuser.getName(), nowuser.getPhone(), nowuser.getAge(), nowuser.isMale(), PaymentMethod.KAKAO,
//                ApplicationStatus.PENDING, false, true, null, "good!!", 4.5, now));
//
//        nowuser = userRepository.findUserById("akak8282");
//        entrantList.add(new Entrant(nowuser, scheduledEventRepository.findScheduledEventById(7),
//                menuRepository.findMenuById(1), nowuser.getName(), nowuser.getPhone(), nowuser.getAge(), nowuser.isMale(), PaymentMethod.KAKAO,
//                ApplicationStatus.PENDING, false, true, null, "또 올 거 같네요", 4.8, now));
//
//        nowuser = userRepository.findUserById("beom1220");
//        entrantList.add(new Entrant(nowuser, scheduledEventRepository.findScheduledEventById(5),
//                menuRepository.findMenuById(1), nowuser.getName(), nowuser.getPhone(), nowuser.getAge(), nowuser.isMale(), PaymentMethod.KAKAO,
//                ApplicationStatus.PENDING, false, true, null, "맘에 드네요", 5.0, now));
//
//        nowuser = userRepository.findUserById("kimmin1");
//        entrantList.add(new Entrant(nowuser, scheduledEventRepository.findScheduledEventById(6),
//                menuRepository.findMenuById(1), nowuser.getName(), nowuser.getPhone(), nowuser.getAge(), nowuser.isMale(), PaymentMethod.KAKAO,
//                ApplicationStatus.PENDING, false, true, null, "good!!", 4.5, now));
//
//        nowuser = userRepository.findUserById("dlwlrma");
//        entrantList.add(new Entrant(nowuser, scheduledEventRepository.findScheduledEventById(7),
//                menuRepository.findMenuById(1), nowuser.getName(), nowuser.getPhone(), nowuser.getAge(), nowuser.isMale(), PaymentMethod.KAKAO,
//                ApplicationStatus.PENDING, false, true, null, "또 올 거 같네요", 4.8, now));
//
//        nowuser = userRepository.findUserById("kimmin1");
//        entrantList.add(new Entrant(nowuser, scheduledEventRepository.findScheduledEventById(5),
//                menuRepository.findMenuById(1), nowuser.getName(), nowuser.getPhone(), nowuser.getAge(), nowuser.isMale(), PaymentMethod.KAKAO,
//                ApplicationStatus.PENDING, false, true, null, "맘에 드네요", 5.0, now));
//
//        nowuser = userRepository.findUserById("dlwlrma");
//        entrantList.add(new Entrant(nowuser, scheduledEventRepository.findScheduledEventById(6),
//                menuRepository.findMenuById(1), nowuser.getName(), nowuser.getPhone(), nowuser.getAge(), nowuser.isMale(), PaymentMethod.KAKAO,
//                ApplicationStatus.PENDING, false, true, null, "good!!", 4.5, now));
//
//        nowuser = userRepository.findUserById("beom1220");
//        entrantList.add(new Entrant(nowuser, scheduledEventRepository.findScheduledEventById(7),
//                menuRepository.findMenuById(1), nowuser.getName(), nowuser.getPhone(), nowuser.getAge(), nowuser.isMale(), PaymentMethod.KAKAO,
//                ApplicationStatus.PENDING, false, true, null, "또 올 거 같네요", 4.8, now));
        entrantRepository.saveAll(entrantList);
    }

    public void ReservationEntity(){
        List<Reservation> reservationList = new ArrayList<>();

        reservationList.add(new Reservation(userRepository.findUserById("beom1220"), "디비팀플모임", 4, 50000,
                PaymentMethod.CREDIT, false, userRepository.findUserById("beom1220").getName(), userRepository.findUserById("beom1220").getPhone(),
                0, false));
        reservationList.add(new Reservation(userRepository.findUserById("tttgood"), "꽃배달 모임", 8, 50000,
                PaymentMethod.CREDIT, false, userRepository.findUserById("tttgood").getName(), userRepository.findUserById("tttgood").getPhone(),
                0, false));
        reservationList.add(new Reservation(userRepository.findUserById("dkanrjsk010"), "아이브 팬카페 정기", 10, 50000,
                PaymentMethod.CREDIT, true, userRepository.findUserById("dkanrjsk010").getName(), userRepository.findUserById("dkanrjsk010").getPhone(),
                0, false));
        reservationList.add(new Reservation(userRepository.findUserById("mrLow"), "저녁 노가리", 3, 50000,
                PaymentMethod.CREDIT, false, userRepository.findUserById("mrLow").getName(), userRepository.findUserById("mrLow").getPhone(),
                0, false));
        reservationList.add(new Reservation(userRepository.findUserById("kimDongSeo"), "커피 탐구회", 10, 100000,
                PaymentMethod.CREDIT, false, userRepository.findUserById("kimDongSeo").getName(), userRepository.findUserById("kimDongSeo").getPhone(),
                0, false));

        reservationList.add(new Reservation(userRepository.findUserById("arirang"), "시 낭송회", 10, 100000,
                PaymentMethod.CREDIT, false, userRepository.findUserById("arirang").getName(), userRepository.findUserById("arirang").getPhone(),
                0, false));
        reservationList.add(new Reservation(userRepository.findUserById("akak8282"), "사대부고 동창회", 10, 100000,
                PaymentMethod.CREDIT, false, userRepository.findUserById("kimDongSeo").getName(), userRepository.findUserById("kimDongSeo").getPhone(),
                0, false));
        reservationList.add(new Reservation(userRepository.findUserById("dndndn23"), "그냥 빌림", 10, 100000,
                PaymentMethod.CREDIT, false, userRepository.findUserById("kimDongSeo").getName(), userRepository.findUserById("kimDongSeo").getPhone(),
                0, false));
        reservationList.add(new Reservation(userRepository.findUserById("alliswell"), "귀사모", 10, 100000,
                PaymentMethod.CREDIT, false, userRepository.findUserById("kimDongSeo").getName(), userRepository.findUserById("kimDongSeo").getPhone(),
                0, false));
        reservationList.add(new Reservation(userRepository.findUserById("coffeemaster"), "스터디 그룹", 10, 100000,
                PaymentMethod.CREDIT, false, userRepository.findUserById("kimDongSeo").getName(), userRepository.findUserById("kimDongSeo").getPhone(),
                0, false));
        reservationList.add(new Reservation(userRepository.findUserById("vlrhsgkek"), "일본어 스터디", 10, 100000,
                PaymentMethod.CREDIT, false, userRepository.findUserById("kimDongSeo").getName(), userRepository.findUserById("kimDongSeo").getPhone(),
                0, false));
        reservationList.add(new Reservation(userRepository.findUserById("wlqdprkrhtlvek"), "조기축구 전술교류", 10, 100000,
                PaymentMethod.CREDIT, false, userRepository.findUserById("kimDongSeo").getName(), userRepository.findUserById("kimDongSeo").getPhone(),
                0, false));
        reservationList.add(new Reservation(userRepository.findUserById("rmaksgkffo"), "과제 모임", 10, 100000,
                PaymentMethod.CREDIT, false, userRepository.findUserById("kimDongSeo").getName(), userRepository.findUserById("kimDongSeo").getPhone(),
                0, false));
        reservationRepository.saveAll(reservationList);
    }

    public void ReservationBlockEntity(){
        List<ReservationBlock> reservationBlockList = new ArrayList<>();

//        reservationBlockList.add(new ReservationBlock(placeRepository.findPlaceById(1), LocalDate.of(2023,12,19),
//                LocalTime.of(10, 00), LocalTime.of(12, 00), false));
//        reservationBlockList.add(new ReservationBlock(placeRepository.findPlaceById(1), LocalDate.of(2023,12,19),
//                LocalTime.of(12, 00), LocalTime.of(14, 00), true));
//        reservationBlockList.add(new ReservationBlock(placeRepository.findPlaceById(1), LocalDate.of(2023,12,19),
//                LocalTime.of(14, 00), LocalTime.of(16, 00), true));
//        reservationBlockList.add(new ReservationBlock(placeRepository.findPlaceById(1), LocalDate.of(2023,12,19),
//                LocalTime.of(16, 00), LocalTime.of(18, 00), true));
//        reservationBlockList.add(new ReservationBlock(placeRepository.findPlaceById(1), LocalDate.of(2023,12,19),
//                LocalTime.of(18, 00), LocalTime.of(20, 00), false));
//
//        reservationBlockList.add(new ReservationBlock(placeRepository.findPlaceById(1), LocalDate.of(2023,12,20),
//                LocalTime.of(10, 00), LocalTime.of(12, 00), true));
//        reservationBlockList.add(new ReservationBlock(placeRepository.findPlaceById(1), LocalDate.of(2023,12,20),
//                LocalTime.of(12, 00), LocalTime.of(14, 00), true));
//        reservationBlockList.add(new ReservationBlock(placeRepository.findPlaceById(1), LocalDate.of(2023,12,20),
//                LocalTime.of(14, 00), LocalTime.of(16, 00), true));
//        reservationBlockList.add(new ReservationBlock(placeRepository.findPlaceById(1), LocalDate.of(2023,12,20),
//                LocalTime.of(16, 00), LocalTime.of(18, 00), true));
//        reservationBlockList.add(new ReservationBlock(placeRepository.findPlaceById(1), LocalDate.of(2023,12,20),
//                LocalTime.of(18, 00), LocalTime.of(20, 00), true));
//
//        reservationBlockList.add(new ReservationBlock(placeRepository.findPlaceById(1), LocalDate.of(2023,12,29),
//                LocalTime.of(10, 00), LocalTime.of(12, 00), true));
//        reservationBlockList.add(new ReservationBlock(placeRepository.findPlaceById(1), LocalDate.of(2024,1,5),
//                LocalTime.of(10, 00), LocalTime.of(12, 00), true));
//        reservationBlockList.add(new ReservationBlock(placeRepository.findPlaceById(1), LocalDate.of(2024,1,12),
//                LocalTime.of(10, 00), LocalTime.of(12, 00), true));
//        reservationBlockList.add(new ReservationBlock(placeRepository.findPlaceById(1), LocalDate.of(2024,1,19),
//                LocalTime.of(10, 00), LocalTime.of(12, 00), true));
//        reservationBlockList.add(new ReservationBlock(placeRepository.findPlaceById(2), LocalDate.of(2024,1,26),
//                LocalTime.of(10, 00), LocalTime.of(12, 00), true));
//
//        reservationBlockList.add(new ReservationBlock(placeRepository.findPlaceById(1), LocalDate.of(2023,12,28),
//                LocalTime.of(10, 00), LocalTime.of(12, 00), true));
//        reservationBlockList.add(new ReservationBlock(placeRepository.findPlaceById(1), LocalDate.of(2024,1,4),
//                LocalTime.of(10, 00), LocalTime.of(12, 00), true));
//        reservationBlockList.add(new ReservationBlock(placeRepository.findPlaceById(1), LocalDate.of(2024,1,11),
//                LocalTime.of(10, 00), LocalTime.of(12, 00), true));
//        reservationBlockList.add(new ReservationBlock(placeRepository.findPlaceById(1), LocalDate.of(2024,1,18),
//                LocalTime.of(10, 00), LocalTime.of(12, 00), true));
//
//        reservationBlockList.add(new ReservationBlock(placeRepository.findPlaceById(1), LocalDate.of(2023,12,28),
//                LocalTime.of(12, 00), LocalTime.of(14, 00), true));
//        reservationBlockList.add(new ReservationBlock(placeRepository.findPlaceById(1), LocalDate.of(2024,1,4),
//                LocalTime.of(12, 00), LocalTime.of(14, 00), true));
//        reservationBlockList.add(new ReservationBlock(placeRepository.findPlaceById(1), LocalDate.of(2024,1,11),
//                LocalTime.of(12, 00), LocalTime.of(14, 00), true));
//        reservationBlockList.add(new ReservationBlock(placeRepository.findPlaceById(1), LocalDate.of(2024,1,18),
//                LocalTime.of(12, 00), LocalTime.of(14, 00), true));

        // 시작 날짜와 끝 날짜 설정
        LocalDate startDate = LocalDate.of(2023, 12, 19);
        LocalDate endDate = LocalDate.of(2024, 2, 1);

// 시작 시간과 끝 시간 조합
        LocalTime[][] times = {
                {LocalTime.of(10, 00), LocalTime.of(12, 00)},
                {LocalTime.of(12, 00), LocalTime.of(14, 00)},
                {LocalTime.of(14, 00), LocalTime.of(16, 00)},
                {LocalTime.of(16, 00), LocalTime.of(18, 00)},
                {LocalTime.of(18, 00), LocalTime.of(20, 00)},
                {LocalTime.of(20, 00), LocalTime.of(22, 00)}
        };

// 시작 날짜부터 끝 날짜까지 반복
        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
            // 각 장소에 대해 반복
            for (int placeId = 1; placeId <= 3; placeId++) {
                // 각 시간 조합에 대해 반복
                for (int i = 0; i < times.length; i++) {
                    reservationBlockList.add(new ReservationBlock(
                            placeRepository.findPlaceById(placeId),
                            date,
                            times[i][0],
                            times[i][1],
                            true
                    ));
                }
            }
        }
        reservationBlockRepository.save(new ReservationBlock(placeRepository.findPlaceById(1), LocalDate.of(2023,10,18),
                LocalTime.of(12, 00), LocalTime.of(14, 00), false));
        reservationBlockRepository.save(new ReservationBlock(placeRepository.findPlaceById(1), LocalDate.of(2023,10,17),
                LocalTime.of(12, 00), LocalTime.of(14, 00), false));
        reservationBlockRepository.save(new ReservationBlock(placeRepository.findPlaceById(1), LocalDate.of(2023,10,16),
                LocalTime.of(12, 00), LocalTime.of(14, 00), false));
        reservationBlockRepository.save(new ReservationBlock(placeRepository.findPlaceById(1), LocalDate.of(2023,10,15),
                LocalTime.of(12, 00), LocalTime.of(14, 00), false));
        reservationBlockRepository.save(new ReservationBlock(placeRepository.findPlaceById(1), LocalDate.of(2023,10,14),
                LocalTime.of(12, 00), LocalTime.of(14, 00), false));
        reservationBlockRepository.save(new ReservationBlock(placeRepository.findPlaceById(1), LocalDate.of(2023,10,13),
                LocalTime.of(12, 00), LocalTime.of(14, 00), false));
        reservationBlockRepository.save(new ReservationBlock(placeRepository.findPlaceById(1), LocalDate.of(2023,10,12),
                LocalTime.of(12, 00), LocalTime.of(14, 00), false));
        reservationBlockRepository.save(new ReservationBlock(placeRepository.findPlaceById(1), LocalDate.of(2023,10,11),
                LocalTime.of(12, 00), LocalTime.of(14, 00), false));

        reservationBlockRepository.saveAll(reservationBlockList);
        reservationBlockList = reservationBlockRepository.findAllByDateAndIsBookableOrderByPlaceIdAsc(LocalDate.of(2023, 12, 20), true);
        reservationBlockList.addAll(reservationBlockRepository.findAllByDateAndIsBookableOrderByPlaceIdAsc(LocalDate.of(2023, 12, 25), true));
        reservationBlockList.addAll(reservationBlockRepository.findAllByDateAndIsBookableOrderByPlaceIdAsc(LocalDate.of(2024, 1, 1), true));
        reservationBlockList.addAll(reservationBlockRepository.findAllByDateAndIsBookableOrderByPlaceIdAsc(LocalDate.of(2024, 1, 7), true));
        reservationBlockList.addAll(reservationBlockRepository.findAllByDateAndIsBookableOrderByPlaceIdAsc(LocalDate.of(2024, 1, 20), true));
        reservationBlockList.addAll(reservationBlockRepository.findAllByDateAndIsBookableOrderByPlaceIdAsc(LocalDate.of(2024, 1, 22), true));

        reservationBlockList.stream().forEach(block -> block.setBookable(false));
        reservationBlockRepository.saveAll(reservationBlockList);

        reservationBlockList = reservationBlockRepository.findAllReservationBlockByDateAndStartTimeAndIsBookable(LocalDate.of(2024, 1, 11),
                LocalTime.of(12, 00), true);
        reservationBlockList.addAll(reservationBlockRepository.findAllReservationBlockByDateAndStartTimeAndIsBookable(LocalDate.of(2024, 1, 11),
                LocalTime.of(16, 00), true));
        reservationBlockList.stream().forEach(block -> block.setBookable(false));
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

        reservationItemList.add(new ReservationItem(reservationRepository.findReservationById(1), reservationBlockRepository.findReservationBlockById(117), "1111",
                50000, settingRepository.findByName("얼리버드할인율").getValue(),
                settingRepository.findByName("주중할인율").getValue(), true));

        reservationItemList.add(new ReservationItem(reservationRepository.findReservationById(2), reservationBlockRepository.findReservationBlockById(118), "1111",
                50000, settingRepository.findByName("얼리버드할인율").getValue(),
                settingRepository.findByName("주중할인율").getValue(), true));

        reservationItemList.add(new ReservationItem(reservationRepository.findReservationById(3), reservationBlockRepository.findReservationBlockById(119), "1111",
                50000, settingRepository.findByName("얼리버드할인율").getValue(),
                settingRepository.findByName("주중할인율").getValue(), true));
        reservationItemList.add(new ReservationItem(reservationRepository.findReservationById(5), reservationBlockRepository.findReservationBlockById(120), "1111",
                50000, settingRepository.findByName("얼리버드할인율").getValue(),
                settingRepository.findByName("주중할인율").getValue(), false));

        reservationItemList.add(new ReservationItem(reservationRepository.findReservationById(5), reservationBlockRepository.findReservationBlockById(121), "1111",
                50000, settingRepository.findByName("얼리버드할인율").getValue(),
                settingRepository.findByName("주중할인율").getValue(), true));



        reservationItemList.add(new ReservationItem(reservationRepository.findReservationById(6), reservationBlockRepository.findReservationBlockById(1), "1111",
                50000, settingRepository.findByName("얼리버드할인율").getValue(),
                settingRepository.findByName("주중할인율").getValue(), true));

        reservationItemList.add(new ReservationItem(reservationRepository.findReservationById(7), reservationBlockRepository.findReservationBlockById(2), "1111",
                50000, settingRepository.findByName("얼리버드할인율").getValue(),
                settingRepository.findByName("주중할인율").getValue(), true));

        reservationItemList.add(new ReservationItem(reservationRepository.findReservationById(8), reservationBlockRepository.findReservationBlockById(3), "1111",
                50000, settingRepository.findByName("얼리버드할인율").getValue(),
                settingRepository.findByName("주중할인율").getValue(), true));

        reservationItemList.add(new ReservationItem(reservationRepository.findReservationById(9), reservationBlockRepository.findReservationBlockById(4), "1111",
                50000, settingRepository.findByName("얼리버드할인율").getValue(),
                settingRepository.findByName("주중할인율").getValue(), true));

        reservationItemList.add(new ReservationItem(reservationRepository.findReservationById(10), reservationBlockRepository.findReservationBlockById(5), "1111",
                50000, settingRepository.findByName("얼리버드할인율").getValue(),
                settingRepository.findByName("주중할인율").getValue(), true));

        reservationItemList.add(new ReservationItem(reservationRepository.findReservationById(11), reservationBlockRepository.findReservationBlockById(6), "1111",
                50000, settingRepository.findByName("얼리버드할인율").getValue(),
                settingRepository.findByName("주중할인율").getValue(), true));

        reservationItemList.add(new ReservationItem(reservationRepository.findReservationById(12), reservationBlockRepository.findReservationBlockById(7), "1111",
                50000, settingRepository.findByName("얼리버드할인율").getValue(),
                settingRepository.findByName("주중할인율").getValue(), true));

        reservationItemList.add(new ReservationItem(reservationRepository.findReservationById(13), reservationBlockRepository.findReservationBlockById(8), "1111",
                50000, settingRepository.findByName("얼리버드할인율").getValue(),
                settingRepository.findByName("주중할인율").getValue(), true));

        reservationItemRepository.saveAll(reservationItemList);
    }

    public void CouponEntity(){
        List<Coupon> couponList = new ArrayList<>();

        couponList.add(new Coupon("웰컴쿠폰", 10, 30000, 30, 100));
        couponList.add(new Coupon("오랜만쿠폰", 30, 70000, 30, 100));
        couponList.add(new Coupon("장기고객쿠폰", 15, 50000, 60, 100));
        couponList.add(new Coupon("명절축하쿠폰", 20, 80000, 15, 100));
        couponList.add(new Coupon("취소보상쿠폰", 20, 100000, 15, 100));
        couponRepository.saveAll(couponList);
    }

    public void OwnCouponEntity(){
        List<OwnCoupon> ownCouponList = new ArrayList<>();

        ownCouponList.add(new OwnCoupon(couponRepository.findCouponById(1), userRepository.findUserById("kimmin1"), Date.valueOf(LocalDate.of(2024,2,15)), CouponStatus.USABLE));
        ownCouponList.add(new OwnCoupon(couponRepository.findCouponById(1), userRepository.findUserById("kimDongSeo"), Date.valueOf(LocalDate.of(2024,2,15)), CouponStatus.USABLE));
        ownCouponList.add(new OwnCoupon(couponRepository.findCouponById(2), userRepository.findUserById("kimDongSeo"), Date.valueOf(LocalDate.of(2024,2,15)), CouponStatus.USABLE));
        ownCouponList.add(new OwnCoupon(couponRepository.findCouponById(2), userRepository.findUserById("mrLow"), Date.valueOf(LocalDate.of(2024,2,15)), CouponStatus.USABLE));
        ownCouponList.add(new OwnCoupon(couponRepository.findCouponById(3), userRepository.findUserById("beom1220"), Date.valueOf(LocalDate.of(2024,2,15)), CouponStatus.USABLE));
        ownCouponList.add(new OwnCoupon(couponRepository.findCouponById(4), userRepository.findUserById("tttgood"), Date.valueOf(LocalDate.of(2024,2,15)), CouponStatus.USABLE));

        ownCouponRepository.saveAll(ownCouponList);
    }

    public void LevelHistoryEntity(){
        List<LevelHistory> levelHistoryList = new ArrayList<>();

        levelHistoryList.add(new LevelHistory(userRepository.findUserById("arirang"), 110, Level.GOLD, 2023, 9));
        levelHistoryList.add(new LevelHistory(userRepository.findUserById("akak8282"), 83, Level.SILVER, 2023, 9));
        levelHistoryList.add(new LevelHistory(userRepository.findUserById("dndndn23"), 80, Level.SILVER, 2023, 9));
        levelHistoryList.add(new LevelHistory(userRepository.findUserById("alliswell"), 180, Level.VIP, 2023, 9));
        levelHistoryList.add(new LevelHistory(userRepository.findUserById("coffeemaster"), 10, Level.BRONZE, 2023, 9));
        levelHistoryList.add(new LevelHistory(userRepository.findUserById("vlrhsgkek"), 90, Level.GOLD, 2023, 9));
        levelHistoryList.add(new LevelHistory(userRepository.findUserById("wlqdprkrhtlvek"), 150, Level.DIAMOND, 2023, 9));
        levelHistoryList.add(new LevelHistory(userRepository.findUserById("rmaksgkffo"), 152, Level.DIAMOND, 2023, 9));

        levelHistoryList.add(new LevelHistory(userRepository.findUserById("kimDongSeo"), 10, Level.BRONZE, 2023, 10));
        levelHistoryList.add(new LevelHistory(userRepository.findUserById("mrLow"), 100, Level.DIAMOND, 2023, 10));
        levelHistoryList.add(new LevelHistory(userRepository.findUserById("kimmin1"), 50, Level.GOLD, 2023, 10));
        levelHistoryList.add(new LevelHistory(userRepository.findUserById("beom1220"), 35, Level.SILVER, 2023, 10));
        levelHistoryList.add(new LevelHistory(userRepository.findUserById("tttgood"), 30, Level.SILVER, 2023, 10));
        levelHistoryList.add(new LevelHistory(userRepository.findUserById("dkanrjsk010"), 33, Level.SILVER, 2023, 10));

        levelHistoryList.add(new LevelHistory(userRepository.findUserById("kimDongSeo"), 7, Level.BRONZE, 2023, 11));
        levelHistoryList.add(new LevelHistory(userRepository.findUserById("mrLow"), 98, Level.DIAMOND, 2023, 11));
        levelHistoryList.add(new LevelHistory(userRepository.findUserById("kimmin1"), 57, Level.GOLD, 2023, 11));
        levelHistoryList.add(new LevelHistory(userRepository.findUserById("beom1220"), 29, Level.SILVER, 2023, 11));
        levelHistoryList.add(new LevelHistory(userRepository.findUserById("tttgood"), 30, Level.SILVER, 2023, 11));
        levelHistoryList.add(new LevelHistory(userRepository.findUserById("dkanrjsk010"), 36, Level.SILVER, 2023, 11));
        levelHistoryList.add(new LevelHistory(userRepository.findUserById("arirang"), 300, Level.VIP, 2023, 11));

        levelHistoryRepository.saveAll(levelHistoryList);
    }

    public void MileageHistoryEntity(){
        List<MileageHistory> mileageHistoryList = new ArrayList<>();

        mileageHistoryList.add(new MileageHistory(userRepository.findUserById("kimDongSeo"), true, 700, "내용없음"));
        mileageHistoryList.add(new MileageHistory(userRepository.findUserById("mrLow"), true, 500, "내용없음"));

        mileageHistoryRepository.saveAll(mileageHistoryList);
    }

    public void PrizeHistoryEntity(){
        List<PrizeHistory> prizeHistoryList = new ArrayList<>();
        prizeHistoryList.add(new PrizeHistory(userRepository.findUserById("kimDongSeo"), prizeRepository.findPrizeById(4)));
        prizeHistoryList.add(new PrizeHistory(userRepository.findUserById("mrLow"), prizeRepository.findPrizeById(4)));
        prizeHistoryList.add(new PrizeHistory(userRepository.findUserById("kimmin1"), prizeRepository.findPrizeById(3)));
        prizeHistoryList.add(new PrizeHistory(userRepository.findUserById("beom1220"), prizeRepository.findPrizeById(3)));
        prizeHistoryList.add(new PrizeHistory(userRepository.findUserById("tttgood"), prizeRepository.findPrizeById(3)));
        prizeHistoryList.add(new PrizeHistory(userRepository.findUserById("tttgood"), prizeRepository.findPrizeById(3)));

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

        suggestionList.add(new Suggestion(userRepository.findUserById("kimDongSeo"), "독서모임요청", "독서 모임 요청합니다.", SuggestionCategory.EVENT, "불만 사항 숙지 후, 개선 요청을 반영하도록 하겠습니다."));
        suggestionList.add(new Suggestion(userRepository.findUserById("mrLow"), "홈페이지 수정 요청", "홈페이지가 너무 눈아파요.", SuggestionCategory.WEB, "속히 UI 변경에 반영토록 하겠습니다."));
        suggestionList.add(new Suggestion(userRepository.findUserById("kimmin1"), "메뉴 가격", "쌍화탕이 너무 비싸요", SuggestionCategory.CAFE, "쌍화탕 가격에는 약재 가격이 다수 포함되어 있는점 양해바랍니다."));

        suggestionRepository.saveAll(suggestionList);
    }

    public void VoucherEntity(){ // 교환권
        List<Voucher> voucherList = new ArrayList<>();

        voucherList.add(new Voucher(userRepository.findUserById("kimDongSeo"), menuRepository.findMenuById(4), menuRepository.findMenuById(4).getPrice(), Date.valueOf(LocalDate.now()), 30, null, CouponStatus.USABLE));
        voucherList.add(new Voucher(userRepository.findUserById("kimDongSeo"), menuRepository.findMenuById(3), menuRepository.findMenuById(3).getPrice(), Date.valueOf(LocalDate.now()), 30, null, CouponStatus.USED));
        voucherList.add(new Voucher(userRepository.findUserById("mrLow"), menuRepository.findMenuById(2), menuRepository.findMenuById(2).getPrice(), Date.valueOf(LocalDate.now()), 30, null, CouponStatus.USABLE));
        voucherList.add(new Voucher(userRepository.findUserById("kimmin1"), menuRepository.findMenuById(4), menuRepository.findMenuById(4).getPrice(), Date.valueOf(LocalDate.now()), 30, null, CouponStatus.USABLE));
        voucherList.add(new Voucher(userRepository.findUserById("beom1220"), menuRepository.findMenuById(4), menuRepository.findMenuById(4).getPrice(), Date.valueOf(LocalDate.now()), 30, null, CouponStatus.USABLE));
        voucherList.add(new Voucher(userRepository.findUserById("tttgood"), menuRepository.findMenuById(4), menuRepository.findMenuById(4).getPrice(), Date.valueOf(LocalDate.now()), 30, null, CouponStatus.USABLE));

        voucherRepository.saveAll(voucherList);
    }

}
