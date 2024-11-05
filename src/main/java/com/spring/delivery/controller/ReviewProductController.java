package com.spring.delivery.controller;

import com.spring.delivery.domain.ApiPaging;
import com.spring.delivery.domain.response.review.ProductReviewResponse;
import com.spring.delivery.domain.response.review.ProductReviewDetailResponse;
import com.spring.delivery.service.business.review.IReviewProductService;
import com.spring.delivery.util.anotation.ApiMessage;
import com.spring.delivery.util.enums.Rating;
import com.spring.delivery.util.enums.converter.SortConverter;
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
@RequestMapping("/api/v1/review")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReviewProductController {
    IReviewProductService reviewProductService;

    @GetMapping("/product/{product_id}")
    @ApiMessage("Review overall for a product")
    public ResponseEntity<ProductReviewResponse> getRating(@PathVariable("product_id") String productId) {
        ProductReviewResponse productReviewResponse = reviewProductService.getRatingOverall(productId);
        log.info("Rating for product id: {} is {}", productId, productReviewResponse);
        return ResponseEntity.ok().body(productReviewResponse);
    }

    @GetMapping("/product")
    @ApiMessage("Review overall (not analyze) for list product")
    public ResponseEntity<List<ProductReviewResponse>> getRatings(@RequestParam("product_id[]") List<String> productIds) {
        List<ProductReviewResponse> productReviewResponses = reviewProductService.getRatingOverall(productIds);
        log.info("Ratings for product ids: {} are {}", productIds, productReviewResponses);
        if (productReviewResponses.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return ResponseEntity.ok().body(productReviewResponses);
    }

    @GetMapping("/product/pageable/{product_id}")
    @ApiMessage("Pageable review for product")
    public ResponseEntity<ApiPaging<ProductReviewDetailResponse>> getReviewDetail(
            @PathVariable("product_id") String productId,
            @RequestParam(value = "rating", defaultValue = "5") int ratingQuery,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sortField", defaultValue = "createdAt") String sortField,
            @RequestParam(value = "sortDirection", defaultValue = "desc") String sortDirection) {
        Pageable pageable = SortConverter.convert(sortField, sortDirection, page, size);
        Rating rating = Rating.fromValue(ratingQuery);
        ApiPaging<ProductReviewDetailResponse> productRatingDTO = reviewProductService.getProductRatingDetail(productId, rating, pageable);
        if (productRatingDTO == null)
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        log.info("Rating for product id: {} is {}", productId, productRatingDTO);
        return ResponseEntity.ok().body(productRatingDTO);
    }
}