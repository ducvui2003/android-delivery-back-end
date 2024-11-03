package com.spring.delivery.domain.request.product;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record RequestProductFilter(
        String name,
        String categoryId,
        Boolean bestSeller,
        Boolean isNew,
        int page
) {
}

