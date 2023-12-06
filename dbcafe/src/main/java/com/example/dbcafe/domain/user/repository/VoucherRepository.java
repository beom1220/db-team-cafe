package com.example.dbcafe.domain.user.repository;

import com.example.dbcafe.domain.user.domain.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoucherRepository extends JpaRepository<Voucher, Integer> {
}