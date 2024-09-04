package com.spring.ratingservice.controller;

import com.spring.ratingservice.domain.ApiPaging;
import com.spring.ratingservice.domain.ProductRatingDTO;
import com.spring.ratingservice.domain.ProductRatingDetailDTO;
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
@RequestMapping("/api/v1/rating")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReviewProductController {
    ReviewProductService reviewProductService;

    @GetMapping("/{product_id}")
    @ApiMessage("Rating overall for a product")
    public ResponseEntity<ProductRatingDTO> getRating(@PathVariable("product_id") String productId) {
        ProductRatingDTO productRatingDTO = reviewProductService.getRatingOverall(productId);
        log.info("Rating for product id: {} is {}", productId, productRatingDTO);
        return ResponseEntity.ok().body(productRatingDTO);
    }

    @GetMapping
    @ApiMessage("Rating overall for list product")
    public ResponseEntity<List<ProductRatingDTO>> getRatings(@RequestParam("product_ids") List<String> productIds) {
        List<ProductRatingDTO> productRatingDTOS = reviewProductService.getRatingOverall(productIds);
        log.info("Ratings for product ids: {} are {}", productIds, productRatingDTOS);
        return ResponseEntity.ok().body(productRatingDTOS);
    }

    @GetMapping("/list/{productId}")
    @ApiMessage("Pageable rating for product")
    public ResponseEntity<ApiPaging<ProductRatingDetailDTO>> getRatingDetail(
            @PathVariable("productId") String productId,
            @RequestParam(value = "rating", defaultValue = "5") int ratingQuery,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sortField", defaultValue = "createdAt") String sortField,
            @RequestParam(value = "sortDirection", defaultValue = "desc") String sortDirection) {
        Pageable pageable = SortConverter.convert(sortField, sortDirection, page, size);
        Rating rating = Rating.fromValue(ratingQuery);
        ApiPaging<ProductRatingDetailDTO> productRatingDTO = reviewProductService.getProductRatingDetail(productId, rating, pageable);
        if (productRatingDTO == null)
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        log.info("Rating for product id: {} is {}", productId, productRatingDTO);
        return ResponseEntity.ok().body(productRatingDTO);
    }
}