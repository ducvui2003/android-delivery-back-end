package com.spring.ratingservice.repository;

import com.spring.ratingservice.domain.ProductRatingDTO;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CustomReviewRepository {
    Optional<ProductRatingDTO> findRatingByProductId(Long productId);

    List<ProductRatingDTO> findRatingByProductIds(Set<Long> productIds);
}
