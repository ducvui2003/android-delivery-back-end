package com.spring.delivery.domain.request.order;

import java.util.List;

public record RequestOrderCreated(
        Double price,
        List<String> images,
        Integer starReview
) {
}
