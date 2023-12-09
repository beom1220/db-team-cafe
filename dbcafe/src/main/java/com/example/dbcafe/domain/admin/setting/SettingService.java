package com.example.dbcafe.domain.admin.setting;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SettingService {
    private final SettingRepository settingRepository;

    public int findValueByName(String name) {
        return settingRepository.findByName(name).getValue();
    }
}
