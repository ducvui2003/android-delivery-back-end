package com.spring.ratingservice.repository;

import com.spring.ratingservice.domain.ProductRatingDTO;
import com.spring.ratingservice.util.constraint.Rating;

import java.util.*;

public interface CustomReviewProductRepository {
    Optional<ProductRatingDTO> findRatingByProductId(String productId);

    Map<String, EnumMap<Rating, Long>> findRatingByProductIds(Set<String> productIds);
}
