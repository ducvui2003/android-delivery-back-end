package com.spring.productservice.domain.response;

public record ResponseProductRating(
        Long productId,
        Double totalRating,
        Integer totalReview,
        Double averageRating
) {
}
