package com.spring.delivery.service.promotion.impl;

import com.spring.delivery.domain.request.promotion.RequestPromotionCreated;
import com.spring.delivery.domain.response.promotion.PromotionDTO;
import com.spring.delivery.mapper.PromotionMapper;
import com.spring.delivery.repository.mongo.PromotionRepository;
import com.spring.delivery.service.promotion.PromotionService;
import com.spring.delivery.util.exception.AppErrorCode;
import com.spring.delivery.util.exception.AppException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PromotionServiceImpl implements PromotionService {
    final PromotionRepository promotionRepository;
    final PromotionMapper mapper;

    @Override
    public List<PromotionDTO> getPromotions() {
        return promotionRepository.findAll().stream().map(mapper::toPromotionDTO).toList();
    }

    @Override
    public PromotionDTO getPromotion(String id) {
        var promotion = promotionRepository.findById(id).orElseThrow();
        log.info("Promotion found: {}", promotion);
        log.info("Promotion found: {}", id);
        return mapper.toPromotionDTO(promotion);
    }

    @Override
    public Boolean hasValidity(String promotionId) {
        var promotion = promotionRepository.findById(promotionId).orElse(null);
        if (promotion == null) throw new AppException(AppErrorCode.PROMOTION_NOT_FOUND);
        var today = LocalDateTime.now();
        var startDate = promotion.getDiscountPromotionInfo().getStartDate();
        var expiredDate = promotion.getDiscountPromotionInfo().getExpired();
        return !today.isBefore(startDate) && !expiredDate.isAfter(today);
    }

    @Override
    public Boolean createPromotion(RequestPromotionCreated req) {
        promotionRepository.save(mapper.toPromotion(req));
        return true;
    }
}
