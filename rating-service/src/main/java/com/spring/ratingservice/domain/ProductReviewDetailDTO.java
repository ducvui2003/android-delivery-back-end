package com.spring.ratingservice.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductReviewDetailDTO {
    UserProductRatingDetailDTO user;
    String content;
    int rating;
    Instant createdAt;

    @Builder
    @Data
    public static class UserProductRatingDetailDTO {
        String userId;
        String fullName;
        String avatar;
    }
}
