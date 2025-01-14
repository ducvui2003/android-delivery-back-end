package com.spring.delivery.domain.response.order;

import com.spring.delivery.util.enums.StatusOrder;

import java.util.List;

public record OrderDTO(
        Long id,
        Double price,
        List<String> images,
        Integer starReview,
        StatusOrder status
) {
}
