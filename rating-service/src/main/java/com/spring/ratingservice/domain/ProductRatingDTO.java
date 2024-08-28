package com.spring.ratingservice.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductRatingDTO {
    Long productId;
    Long totalReview;
    Double averageRating;
}
