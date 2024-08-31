package com.spring.ratingservice.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Map;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductRatingDTO {
    String productId;
    long totalReview;
    double averageRating;
    Map<Integer, Long> ratingDistribution;
}
