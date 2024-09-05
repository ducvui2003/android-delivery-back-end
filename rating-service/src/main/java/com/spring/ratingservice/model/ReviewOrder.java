package com.spring.ratingservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "review_orders")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReviewOrder extends BaseModel {
    String orderId;
    String userId;
    String content;
}
