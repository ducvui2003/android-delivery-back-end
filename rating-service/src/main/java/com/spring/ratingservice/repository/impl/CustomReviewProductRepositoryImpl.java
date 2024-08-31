package com.spring.ratingservice.repository.impl;

import com.spring.ratingservice.repository.CustomReviewProductRepository;
import com.spring.ratingservice.util.constraint.Rating;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class CustomReviewProductRepositoryImpl implements CustomReviewProductRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<EnumMap<Rating, Long>> findRatingByProductId(String productId) {
        EnumMap<Rating, Long> ratingMap = new EnumMap<>(Rating.class);
        String jpql = """
                   SELECT review.productId, rating,  COUNT(review.rating)
                    FROM ReviewProduct review
                    WHERE review.productId = :productId
                    GROUP BY review.rating
                """;

        List<Object[]> results = entityManager.createQuery(jpql).setParameter("productId", productId).getResultList();

        if (results.isEmpty())
            return Optional.empty();

        for (Object[] result : results) {
            Rating rating = (Rating) result[1];
            long count = (Long) result[2];
            ratingMap.put(rating, count);
        }

        return Optional.of(ratingMap);
    }

    @Override
    public Map<String, EnumMap<Rating, Long>> findRatingByProductIds(Set<String> productIds) {
        Map<String, EnumMap<Rating, Long>> productRatingMap = new HashMap<>();
        String jpql = """
                    SELECT review.productId, rating,  COUNT(review.rating)
                    FROM ReviewProduct review
                    WHERE review.productId IN :productIds
                    GROUP BY review.rating
                """;

        List<Object[]> results = entityManager.createQuery(jpql).setParameter("productIds", productIds).getResultList();

        if (results.isEmpty())
            return productRatingMap;

        for (Object[] result : results) {
            String productId = (String) result[0];
            Rating rating = (Rating) result[1];
            long count = (Long) result[2];
            EnumMap<Rating, Long> ratingMap;
            if (productRatingMap.containsKey(productId)) {
                ratingMap = productRatingMap.get(productId);
            } else {
                ratingMap = new EnumMap<>(Rating.class);
            }
            ratingMap.put(rating, count);
            productRatingMap.put(productId, ratingMap);
        }
        return productRatingMap;
    }
}
