package com.spring.ratingservice.controller;

import com.spring.ratingservice.domain.ProductRatingDTO;
import com.spring.ratingservice.service.ReviewService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rating")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class RatingController {
    ReviewService reviewService;

    @GetMapping("/{productId}")
    public ResponseEntity<ProductRatingDTO> getRating(@PathVariable("productId") Long productId) {
        ProductRatingDTO productRatingDTO = reviewService.getProductRating(productId);
        log.info("Rating for product id: {} is {}", productId, productRatingDTO);
        return ResponseEntity.ok().body(productRatingDTO);
    }

    @GetMapping("/list")
    public ResponseEntity<List<ProductRatingDTO>> getRatings(@RequestParam("productIds") List<Long> productIds) {
        List<ProductRatingDTO> productRatingDTOS = reviewService.getProductRating(productIds);
        log.info("Ratings for product ids: {} are {}", productIds, productRatingDTOS);
        return ResponseEntity.ok().body(productRatingDTOS);
    }
}
