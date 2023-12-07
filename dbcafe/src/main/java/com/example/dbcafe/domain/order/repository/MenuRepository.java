package com.example.dbcafe.domain.order.repository;

import com.example.dbcafe.domain.order.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Integer> {
    List<Menu> findAllByNameContainsAndIsSelling(String keyword, boolean b);
}