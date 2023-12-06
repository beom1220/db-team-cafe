package com.example.dbcafe.domain.order.repository;

import com.example.dbcafe.domain.order.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Integer> {
}