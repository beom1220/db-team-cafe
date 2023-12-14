package com.example.dbcafe.domain.reservation.repository;

import com.example.dbcafe.domain.reservation.domain.Place;
import com.example.dbcafe.domain.reservation.domain.ReservationBlock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ReservationBlockRepository extends JpaRepository<ReservationBlock, Integer> {
    List<ReservationBlock> findByDateGreaterThanEqual(LocalDate today); // 예약 페이지에서는 전부 보내서 bookable여부에 따라 다르게 보여야 해서 다 보냄.

    List<ReservationBlock> findByDate(LocalDate date);

    ReservationBlock findFirstByDateAndStartTimeAndIsBookableOrderByPlaceIdAsc(LocalDate date, LocalTime startTime, boolean b);

    List<ReservationBlock> findAllReservationBlockByDateAndStartTimeAndIsBookableFalse(LocalDate date, LocalTime startTime);

    ReservationBlock findReservationBlockByPlaceAndDateAndStartTime(Place place, LocalDate date, LocalTime startTime);

    List<ReservationBlock> findByDateGreaterThanEqualAndIsBookableTrueOrderByDateAscStartTimeAsc(LocalDate now);
    ReservationBlock findReservationBlockById(int id);

    List<ReservationBlock> findAllByIsBookableAndDateGreaterThanEqualOrderByDateAscStartTimeAsc(boolean b, LocalDate today);

    List<ReservationBlock> findAllByIsBookableAndDayOfWeekAndStartTimeAndDateGreaterThanEqualOrderByDateAsc(boolean b, DayOfWeek dow, LocalTime time, LocalDate today);

    List<ReservationBlock> findAllByIsBookableAndStartTimeAndDateGreaterThanEqualOrderByDateAsc(boolean b, LocalTime time, LocalDate today);

    List<ReservationBlock> findAllByIsBookableAndDayOfWeekAndDateGreaterThanEqualOrderByDateAscStartTimeAsc(boolean b, DayOfWeek dow, LocalDate today);

    @Query("SELECT rb FROM ReservationBlock rb WHERE " +
            "rb.isBookable = true AND " +
            "rb.date >= :today AND " +
            "rb.id IN (" +
            "SELECT MIN(rb2.id) FROM ReservationBlock rb2 " +
            "WHERE rb2.isBookable = true AND rb2.date >= :today " +
            "GROUP BY rb2.date, rb2.startTime)")
    List<ReservationBlock> findDistinctByIsBookableAndDateGreaterThanEqualOrderByDateAscStartTimeAsc(LocalDate today);

    @Query("SELECT rb FROM ReservationBlock rb WHERE " +
            "rb.isBookable = true AND " +
            "rb.dayOfWeek = :dayOfWeek AND " +
            "rb.startTime = :startTime AND " +
            "rb.date >= :today AND " +
            "rb.id IN (" +
            "SELECT MIN(rb2.id) FROM ReservationBlock rb2 " +
            "WHERE rb2.isBookable = true AND rb2.dayOfWeek = :dayOfWeek AND rb2.startTime = :startTime AND rb2.date >= :today " +
            "GROUP BY rb2.date)")
    List<ReservationBlock> findDistinctByIsBookableAndDayOfWeekAndStartTimeAndDateGreaterThanEqualOrderByDateAsc(
            DayOfWeek dayOfWeek,
            LocalTime startTime,
            LocalDate today);

    @Query("SELECT rb FROM ReservationBlock rb WHERE " +
            "rb.isBookable = true AND " +
            "rb.startTime = :startTime AND " +
            "rb.date >= :today AND " +
            "rb.id IN (" +
            "SELECT MIN(rb2.id) FROM ReservationBlock rb2 " +
            "WHERE rb2.isBookable = true AND rb2.startTime = :startTime AND rb2.date >= :today " +
            "GROUP BY rb2.date)")
    List<ReservationBlock> findDistinctByIsBookableAndStartTimeAndDateGreaterThanEqualOrderByDateAsc(
            LocalTime startTime,
            LocalDate today);


    @Query("SELECT rb FROM ReservationBlock rb WHERE " +
            "rb.isBookable = true AND " +
            "rb.dayOfWeek = :dayOfWeek AND " +
            "rb.date >= :today AND " +
            "rb.id IN (" +
            "SELECT MIN(rb2.id) FROM ReservationBlock rb2 " +
            "WHERE rb2.isBookable = true AND rb2.dayOfWeek = :dayOfWeek AND rb2.date >= :today " +
            "GROUP BY rb2.date, rb2.startTime)")
    List<ReservationBlock> findDistinctByIsBookableAndDayOfWeekAndDateGreaterThanEqualOrderByDateAscStartTimeAsc(
            DayOfWeek dayOfWeek,
            LocalDate today);
}