package com.spring.ratingservice.model;

import com.spring.ratingservice.util.constraint.Rating;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "review_shippers")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReviewShipper extends BaseModel {
    String shipperId;
    String userId;
    String content;
}
