package com.spring.ratingservice.repository;

import com.spring.ratingservice.domain.ProductReviewDTO;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CustomReviewRepository {
    Optional<ProductReviewDTO> findRatingByProductId(String productId);

    List<ProductReviewDTO> findRatingByProductIds(Set<String> productIds);
}
