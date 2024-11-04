package com.spring.delivery.controller;

import com.spring.delivery.domain.response.promotion.PromotionDTO;
import com.spring.delivery.service.promotion.PromotionService;
import com.spring.delivery.util.anotation.ApiMessage;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/promotion")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class PromotionController {
    PromotionService promotionService;

    @GetMapping("/")
    @ApiMessage("Get all promotions")
    public ResponseEntity<List<PromotionDTO>> getPromotions() {
        return ResponseEntity.ok().body(promotionService.getPromotions());
    }
}
