package com.spring.delivery.service.promotion;

import com.spring.delivery.domain.request.promotion.RequestPromotionCreated;
import com.spring.delivery.domain.response.promotion.PromotionDTO;

import java.util.List;

public interface PromotionService {
    List<PromotionDTO> getPromotions();
    PromotionDTO getPromotion(String id);
    Boolean hasValidity(String promotionId);
    PromotionDTO createPromotion(RequestPromotionCreated req);
}
