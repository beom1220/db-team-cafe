package com.example.dbcafe.domain.reservation.service;

import com.example.dbcafe.domain.reservation.domain.ReservationBlock;
import com.example.dbcafe.domain.reservation.domain.ReservationChecker;
import com.example.dbcafe.domain.reservation.repository.ReservationCheckerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class ReservationCheckerService {
    private final ReservationCheckerRepository reservationCheckerRepository;

    public void updateChecker(ReservationBlock block) {
        LocalTime startTime = block.getStartTime();
        LocalTime a = LocalTime.of(10, 0);
        LocalTime b = LocalTime.of(12, 0);
        LocalTime c = LocalTime.of(14, 0);
        LocalTime d = LocalTime.of(16, 0);
        LocalTime e = LocalTime.of(18, 0);
        LocalTime f = LocalTime.of(20, 0);

        ReservationChecker checker = reservationCheckerRepository.findReservationCheckerByDate(block.getDate());

        if (startTime.equals(a)) {
            checker.setNumA(checker.getNumA() - 1);
        } else if (startTime.equals(b)) {
            checker.setNumB(checker.getNumB() - 1);
        } else if (startTime.equals(c)) {
            checker.setNumC(checker.getNumC() - 1);
        } else if (startTime.equals(d)) {
            checker.setNumD(checker.getNumD() - 1);
        } else if (startTime.equals(e)) {
            checker.setNumE(checker.getNumE() - 1);
        } else if (startTime.equals(f)) {
            checker.setNumF(checker.getNumF() - 1);
        } else {
            return;
        }
        reservationCheckerRepository.save(checker);
    }
}
