package com.spring.delivery.service.promotion.impl;

import com.spring.delivery.domain.request.promotion.RequestPromotionCreated;
import com.spring.delivery.domain.response.promotion.PromotionBaseDTO;
import com.spring.delivery.domain.response.promotion.PromotionDTO;
import com.spring.delivery.mapper.PromotionMapper;
import com.spring.delivery.repository.mongo.PromotionRepository;
import com.spring.delivery.repository.mysql.UserRepository;
import com.spring.delivery.service.promotion.PromotionService;
import com.spring.delivery.util.exception.AppErrorCode;
import com.spring.delivery.util.exception.AppException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PromotionServiceImpl implements PromotionService {
    PromotionRepository promotionRepository;
    PromotionMapper promotionMapper;
    UserRepository userRepository;

    @Override
    public List<PromotionBaseDTO> getPromotions() {
        return promotionRepository.findAll().stream().map(promotionMapper::toPromotionBaseDTO).toList();
    }

    @Override
    public PromotionDTO getPromotion(String id) {
        return promotionMapper.toPromotionDTO(promotionRepository.findById(id).orElseThrow(() -> new AppException(AppErrorCode.PROMOTION_NOT_FOUND)));
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
    public PromotionDTO createPromotion(RequestPromotionCreated req) {
        if (req.userIds() != null)
            if (userRepository.findByIdIn(req.userIds()).isEmpty()) throw new AppException(AppErrorCode.USER_NOT_FOUND);
        var promotion = promotionRepository.save(promotionMapper.toPromotion(req));
        return promotionMapper.toPromotionDTO(promotion);
    }

    @Override
    public List<PromotionBaseDTO> getPromotionsByUserId(Long userId) {
        return promotionRepository.findPromotionsByUserId(userId).stream().map(promotionMapper::toPromotionBaseDTO).toList();
    }
}
