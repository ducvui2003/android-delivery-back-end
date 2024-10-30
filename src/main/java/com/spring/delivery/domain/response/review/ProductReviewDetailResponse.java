package com.spring.delivery.domain.response.review;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductReviewDetailResponse {
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
