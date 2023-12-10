package com.example.dbcafe.domain.order.repository;

import com.example.dbcafe.domain.order.domain.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Integer> {
    List<Orders> findAllOrdersByLevelDiscountRatio(int discountRatio);

    List<Orders> findAllOrdersByLevelDiscountRatioNot(int i);
}