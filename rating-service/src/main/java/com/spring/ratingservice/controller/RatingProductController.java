package com.spring.ratingservice.controller;

import com.spring.ratingservice.domain.ProductRatingDTO;
import com.spring.ratingservice.domain.ProductRatingDetailDTO;
import com.spring.ratingservice.service.ReviewProductService;
import com.spring.ratingservice.util.anotation.ApiMessage;
import com.spring.ratingservice.util.convert.SortConverter;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/rating")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RatingProductController {
    ReviewProductService reviewProductService;

    @GetMapping("/{productId}")
    public ResponseEntity<ProductRatingDTO> getRating(@PathVariable("productId") String productId) {
        ProductRatingDTO productRatingDTO = reviewProductService.getRatingOverall(productId);
        log.info("Rating for product id: {} is {}", productId, productRatingDTO);
        return ResponseEntity.ok().body(productRatingDTO);
    }

    @GetMapping("/")
    @ApiMessage("Rating overall for list product")
    public ResponseEntity<List<ProductRatingDTO>> getRatings(@RequestParam("productIds") List<String> productIds) {
        List<ProductRatingDTO> productRatingDTOS = reviewProductService.getRatingOverall(productIds);
        log.info("Ratings for product ids: {} are {}", productIds, productRatingDTOS);
        return ResponseEntity.ok().body(productRatingDTOS);
    }

    @GetMapping("/list/{productId}")
    @ApiMessage("Pageable rating for product")
    public ResponseEntity<ProductRatingDetailDTO> getRatingDetail(
            @PathVariable("productId") String productId,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sortField", defaultValue = "createdAt") String sortField,
            @RequestParam(value = "sortDirection", defaultValue = "desc") String sortDirection) {
        Pageable pageable = SortConverter.convert(sortField, sortDirection, page, size);
        ProductRatingDetailDTO productRatingDTO = reviewProductService.getProductRatingDetail(productId, pageable);
        log.info("Rating for product id: {} is {}", productId, productRatingDTO);
        return ResponseEntity.ok().body(productRatingDTO);
    }
}