package com.spring.ratingservice.repository;

import com.spring.ratingservice.util.constraint.Rating;

import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public interface CustomReviewProductRepository {
    Optional<EnumMap<Rating, Long>> findRatingByProductId(String productId);

    Map<String, EnumMap<Rating, Long>> findRatingByProductIds(Set<String> productIds);
}
