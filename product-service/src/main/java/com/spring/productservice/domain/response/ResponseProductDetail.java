package com.spring.productservice.domain.response;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class ResponseProductDetail {
    Long id;
    String name;
    String description;
    double price;
    String category;
    String[] images;
    double rating;
//    Option...
}
