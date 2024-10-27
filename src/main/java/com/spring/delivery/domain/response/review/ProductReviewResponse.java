package com.spring.delivery.domain.response.review;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
@Builder
@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductReviewResponse {
    String productId;
    long totalReview;
    double averageRating;
    Map<String, Integer> ratingDistribution;

    public ProductReviewResponse(String productId, long totalReview, double averageRating) {
        this.productId = productId;
        this.totalReview = totalReview;
        this.averageRating = averageRating;
    }

    public ProductReviewResponse(String productId, long totalReview, double averageRating, String ratingDistribution) throws JsonProcessingException {
        this.productId = productId;
        this.totalReview = totalReview;
        this.averageRating = averageRating;
        this.ratingDistribution = new ObjectMapper().readValue(ratingDistribution, Map.class);
    }
}
