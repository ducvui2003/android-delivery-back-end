package com.spring.productservice.domain.response;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@Builder
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class ResponseProduct {
    Long id;
    String name;
    String description;
    BigDecimal basePrice;
    BigDecimal salePrice;
    double rating;
    String category;
    String thumbnail;
}
