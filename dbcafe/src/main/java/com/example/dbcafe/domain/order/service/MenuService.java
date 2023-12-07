package com.example.dbcafe.domain.order.service;

import com.example.dbcafe.domain.order.domain.Menu;
import com.example.dbcafe.domain.order.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {
    private final MenuRepository menuRepository;

    public List<Menu> findAllByKeyword(String keyword) {
        return menuRepository.findAllByNameContainsAndIsSelling(keyword, true);
    }
}
