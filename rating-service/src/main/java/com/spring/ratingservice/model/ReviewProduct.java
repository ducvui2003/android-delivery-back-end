package com.spring.ratingservice.model;

import com.spring.ratingservice.util.constraint.Rating;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "review_products")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReviewProduct extends BaseModel {
    String productId;
    String userId;
    String content;
}
