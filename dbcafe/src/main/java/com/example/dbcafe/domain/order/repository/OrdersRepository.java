package com.example.dbcafe.domain.order.repository;

import com.example.dbcafe.domain.order.domain.Orders;
import com.example.dbcafe.domain.user.domain.Level;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Integer> {
    List<Orders> findAllOrdersByLevel(Level level);
    Orders findOrdersById(int id);

    List<Orders> findAllOrdersByLevelDiscountRatioNot(int i);
}