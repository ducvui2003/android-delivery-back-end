package com.spring.delivery.domain.request;

import com.spring.delivery.util.enums.StatusOrder;

import java.util.List;

public record RequestOrderCreated(
        Double price,
        List<String> images,
        Integer starReview
) {
}
