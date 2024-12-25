package com.spring.delivery.domain.request.cart;

import java.util.List;

public record RequestCartCreated
        (String productId, Integer quantity, List<String> optionIds) {
}
