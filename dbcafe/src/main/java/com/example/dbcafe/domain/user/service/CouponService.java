package com.example.dbcafe.domain.user.service;

import com.example.dbcafe.domain.user.domain.Coupon;
import com.example.dbcafe.domain.user.domain.OwnCoupon;
import com.example.dbcafe.domain.user.dto.CouponDto;
import com.example.dbcafe.domain.user.dto.CouponInfoDto;
import com.example.dbcafe.domain.user.repository.CouponRepository;
import com.example.dbcafe.domain.user.repository.OwnCouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CouponService {
    private final CouponRepository couponRepository;
    private final OwnCouponRepository ownCouponRepository;

    public List<CouponDto> findAlCoupon() {
        List<CouponDto> dtos = new ArrayList<>();
        List<Coupon> coupons = couponRepository.findAllCoupon();
        for (Coupon c : coupons) {
            List<OwnCoupon> ownCoupons = ownCouponRepository.findAllOwnCouponByCouponAndOrdersIsNotNull(c);
            int totalDiscount = 0;
            for (OwnCoupon o : ownCoupons) {
                totalDiscount += o.getDiscountPrice();
            }
            CouponDto dto = new CouponDto(c.getId(), c.getName(), c.getDiscountRatio(),
                    c.getPeriod(), c.getMaxDiscount(), c.getMaxIssuance(),
                    c.getIssuance(), ownCoupons.size(), totalDiscount);
            dtos.add(dto);
        }
        return dtos;
    }

    public Coupon addCoupon(CouponInfoDto dto) {
        Coupon coupon = new Coupon(dto.getName(), dto.getDiscountRatio(),
                dto.getMaxDiscount(), dto.getPeriod(), dto.getMaxIssuance());
        return couponRepository.save(coupon);
    }

    public Coupon editCoupon(int couponId, CouponInfoDto dto) {
        Coupon coupon = couponRepository.findCouponById(couponId);
        coupon.setName(dto.getName());
        coupon.setPeriod(dto.getPeriod());
        coupon.setDiscountRatio(dto.getDiscountRatio());
        coupon.setMaxIssuance(dto.getMaxIssuance());
        coupon.setMaxDiscount(dto.getMaxDiscount());
        return couponRepository.save(coupon);
    }
}
