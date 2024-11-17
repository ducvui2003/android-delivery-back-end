package com.spring.delivery.controller;

import com.spring.delivery.domain.request.promotion.RequestPromotionCreated;
import com.spring.delivery.domain.response.promotion.PromotionBaseDTO;
import com.spring.delivery.domain.response.promotion.PromotionDTO;
import com.spring.delivery.service.promotion.PromotionService;
import com.spring.delivery.util.anotation.ApiMessage;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/promotion")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class PromotionController {
    PromotionService promotionService;

    @GetMapping("/")
    @ApiMessage("Get all promotions")
    public ResponseEntity<List<PromotionBaseDTO>> getPromotions() {
        return ResponseEntity.ok().body(promotionService.getPromotions());
    }

    @GetMapping("/{userId}")
    @ApiMessage("Get promotions by user id")
    public ResponseEntity<List<PromotionBaseDTO>> getPromotionsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok().body(promotionService.getPromotionsByUserId(userId));
    }

    @GetMapping("/get")
    @ApiMessage("Get promotion by id")
    public ResponseEntity<PromotionDTO> getPromotionById(@RequestParam String id) {
        return ResponseEntity.ok().body(promotionService.getPromotion(id));
    }

    @PostMapping("/add")
    @ApiMessage("add promotion")
    public ResponseEntity<PromotionDTO> addPromotion(@Valid @RequestBody RequestPromotionCreated req) {
        return ResponseEntity.ok().body(promotionService.createPromotion(req));
    }
}
