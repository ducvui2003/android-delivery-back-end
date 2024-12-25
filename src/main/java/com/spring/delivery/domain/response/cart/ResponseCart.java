package com.spring.delivery.domain.response.cart;

import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class ResponseCart {
    Long id;
    String productId;
    List<String> optionIds;
    Integer quantity;
}
