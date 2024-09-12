package com.spring.ratingservice.service.specification;

import com.spring.ratingservice.model.ReviewProduct;
import com.spring.ratingservice.util.constraint.Rating;
import org.springframework.data.jpa.domain.Specification;

public class ReviewProductSpecs {
    private ReviewProductSpecs() {
    }

    public static Specification<ReviewProduct> hasRating(Rating rating) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("rating"), rating);
    }

    public static Specification<ReviewProduct> hasProductId(String productId) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("productId"), productId);
    }
}
