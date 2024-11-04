package com.spring.delivery.service.promotion;

import com.spring.delivery.domain.response.promotion.PromotionDTO;

import java.util.List;

public interface PromotionService {
    List<PromotionDTO> getPromotions();
}
