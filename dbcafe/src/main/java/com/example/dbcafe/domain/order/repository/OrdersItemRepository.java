
package com.example.dbcafe.domain.order.repository;

import com.example.dbcafe.domain.order.domain.OrdersItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersItemRepository extends JpaRepository<OrdersItem, Integer> {
}