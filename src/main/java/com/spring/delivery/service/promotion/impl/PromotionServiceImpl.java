package com.spring.delivery.service.promotion.impl;

import com.spring.delivery.domain.response.promotion.PromotionDTO;
import com.spring.delivery.mapper.PromotionMapper;
import com.spring.delivery.repository.mongo.PromotionRepository;
import com.spring.delivery.service.promotion.PromotionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
}
