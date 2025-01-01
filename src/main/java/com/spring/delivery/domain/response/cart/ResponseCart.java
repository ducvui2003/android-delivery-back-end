package com.spring.delivery.domain.response.cart;

import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class ResponseCart {
    Long id;
    String productId;
    Integer quantity;
    String name;
    String thumbnail;
    double price;
    List<Option> options;

    @Data
    @FieldDefaults(level = lombok.AccessLevel.PRIVATE)
    public static class Option {
        String id;
        String name;
        double price;
    }
}
