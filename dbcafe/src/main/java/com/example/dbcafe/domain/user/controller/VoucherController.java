package com.example.dbcafe.domain.user.controller;

import com.example.dbcafe.domain.user.service.VoucherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/voucher")
public class VoucherController {
    private final VoucherService voucherService;
}
