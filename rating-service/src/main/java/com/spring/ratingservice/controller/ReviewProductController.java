package com.spring.ratingservice.controller;

import com.spring.ratingservice.domain.ApiPaging;
import com.spring.ratingservice.domain.ProductReviewDTO;
import com.spring.ratingservice.domain.ProductReviewDetailDTO;
import com.spring.ratingservice.service.ReviewProductService;
import com.spring.ratingservice.util.anotation.ApiMessage;
import com.spring.ratingservice.util.constraint.Rating;
import com.spring.ratingservice.util.convert.SortConverter;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/review/product")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReviewProductController {
    ReviewProductService reviewProductService;

    @GetMapping("/{product_id}")
    @ApiMessage("Review overall for a product")
    public ResponseEntity<ProductReviewDTO> getRating(@PathVariable("product_id") String productId) {
        ProductReviewDTO productReviewDTO = reviewProductService.getRatingOverall(productId);
        log.info("Rating for product id: {} is {}", productId, productReviewDTO);
        return ResponseEntity.ok().body(productReviewDTO);
    }

    @GetMapping
    @ApiMessage("Review overall (not analyze) for list product")
    public ResponseEntity<List<ProductReviewDTO>> getRatings(@RequestParam("product_id") List<String> productIds) {
        List<ProductReviewDTO> productReviewDTOS = reviewProductService.getRatingOverall(productIds);
        log.info("Ratings for product ids: {} are {}", productIds, productReviewDTOS);
        return ResponseEntity.ok().body(productReviewDTOS);
    }

    @GetMapping("/pageable/{product_id}")
    @ApiMessage("Pageable review for product")
    public ResponseEntity<ApiPaging<ProductReviewDetailDTO>> getReviewDetail(
            @PathVariable("product_id") String productId,
            @RequestParam(value = "rating", defaultValue = "5") int ratingQuery,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sortField", defaultValue = "createdAt") String sortField,
            @RequestParam(value = "sortDirection", defaultValue = "desc") String sortDirection) {
        Pageable pageable = SortConverter.convert(sortField, sortDirection, page, size);
        Rating rating = Rating.fromValue(ratingQuery);
        ApiPaging<ProductReviewDetailDTO> productRatingDTO = reviewProductService.getProductRatingDetail(productId, rating, pageable);
        if (productRatingDTO == null)
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        log.info("Rating for product id: {} is {}", productId, productRatingDTO);
        return ResponseEntity.ok().body(productRatingDTO);
    }
}