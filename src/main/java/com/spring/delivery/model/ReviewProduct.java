package com.spring.delivery.model;

import com.spring.delivery.util.enums.Rating;
import com.spring.delivery.util.enums.converter.RatingConverter;
import jakarta.persistence.Convert;
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
    @Convert(converter = RatingConverter.class)
    Rating rating;
    String productId;
    String userId;
    String content;
}
