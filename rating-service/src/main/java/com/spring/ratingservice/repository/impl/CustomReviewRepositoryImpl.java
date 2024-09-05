package com.spring.ratingservice.repository.impl;

import com.spring.ratingservice.domain.ProductRatingDTO;
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
    public Optional<ProductRatingDTO> findRatingByProductId(Long productId) {
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
        Long productIdResult = (Long) result[0];
        Long totalReview = (Long) result[1];
        Double averageRating = (Double) result[2];

        return Optional.of(new ProductRatingDTO(productIdResult, totalReview, averageRating));
    }

    @Override
    public List<ProductRatingDTO> findRatingByProductIds(Set<Long> productIds) {
        List<ProductRatingDTO> productRatingDTOS = new ArrayList<>();
        String jpql = """
                    SELECT review.productId AS productId, COUNT(review.productId) AS totalReview, AVG(review.rating) AS averageRating
                    FROM Review review
                    WHERE review.productId IN :productIds
                    GROUP BY review.productId
                """;

        List<Object[]> results = entityManager.createQuery(jpql).setParameter("productIds", productIds).getResultList();

        if (results.isEmpty())
            return productRatingDTOS;

        for (Object[] result : results) {
            Long productIdResult = (Long) result[0];
            Long totalReview = (Long) result[1];
            Double averageRating = (Double) result[2];
            productRatingDTOS.add(new ProductRatingDTO(productIdResult, totalReview, averageRating));
        }

        return productRatingDTOS;
    }
}
