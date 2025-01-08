package com.spring.delivery.domain.response.cart;

import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class ResponseCart {
    Long id;
    String productId;
    int quantity;
    int quantityValid;
    String name;
    String thumbnail;
    double price;
    List<Option> options;
    double discount;

    @Data
    @FieldDefaults(level = lombok.AccessLevel.PRIVATE)
    public static class Option {
        String id;
        String name;
        double price;
    }
}
