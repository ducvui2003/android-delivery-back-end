package com.spring.ratingservice.repository.impl;

import com.spring.ratingservice.domain.ProductReviewDTO;
import com.spring.ratingservice.repository.CustomReviewRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public class CustomReviewRepositoryImpl implements CustomReviewRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<ProductReviewDTO> findRatingByProductId(String productId) {
        String jpql = """
                    SELECT review.productId AS productId, COUNT(review.productId) AS totalReview, AVG(review.rating) AS averageRating
                    FROM Review review
                    WHERE review.productId = :productId
                    GROUP BY review.productId
                """;

        List<Object[]> results = entityManager.createQuery(jpql).setParameter("productId", productId).getResultList();

        if (results.isEmpty())
            return Optional.empty();

        Object[] result = results.getFirst();
        String productIdResult = (String) result[0];
        Long totalReview = (Long) result[1];
        Double averageRating = (Double) result[2];

        return Optional.of(ProductReviewDTO.builder().productId(productIdResult).totalReview(totalReview).averageRating(averageRating).build());
    }

    @Override
    public List<ProductReviewDTO> findRatingByProductIds(Set<String> productIds) {
        List<ProductReviewDTO> productReviewDTOS = new ArrayList<>();
        String jpql = """
                    SELECT review.productId AS productId, COUNT(review.productId) AS totalReview, AVG(review.rating) AS averageRating
                    FROM Review review
                    WHERE review.productId IN :productIds
                    GROUP BY review.productId
                """;

        List<Object[]> results = entityManager.createQuery(jpql).setParameter("productIds", productIds).getResultList();

        if (results.isEmpty())
            return productReviewDTOS;

        for (Object[] result : results) {
            String productIdResult = (String) result[0];
            Long totalReview = (Long) result[1];
            Double averageRating = (Double) result[2];
            productReviewDTOS.add(ProductReviewDTO.builder().productId(productIdResult).totalReview(totalReview).averageRating(averageRating).build());
        }

        return productReviewDTOS;
    }
}
