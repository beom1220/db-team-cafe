package com.example.dbcafe.domain.reservation.repository;

import com.example.dbcafe.domain.reservation.domain.ReservationItem;
import com.example.dbcafe.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReservationItemRepository extends JpaRepository<ReservationItem, Integer> {
    ReservationItem findByIdAndTempPw(int itemId, String tempPw);

    List<ReservationItem> findAllReservationItemByReservationBlockDateGreaterThanEqualOrderByReservationBlockDateAscReservationBlockStartTimeAsc(LocalDate now);

    ReservationItem findReservationItemById(int reservationItemId);

    ReservationItem findByReservationUserAndLast(User user, boolean b);

    List<ReservationItem> findAllByLastAndKeepingAndReservationBlockDateBetweenAndReservationUserAccumulationGreaterThanEqualOrderByReservationBlockDateAscReservationUserAccumulationDesc(boolean b, boolean b1, LocalDate startDate, LocalDate endDate, int i);
}