package com.spring.delivery.domain.request.product;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record RequestProductCreated(
        String name,

        Double price,

        Integer quantity,

        String description,

        String categoryId,

        RequestDiscountCreated discountInfo,

        @NotNull(message = "Product option Id is required")
        @NotEmpty(message = "Product option Id cannot be empty")
        List<@NotEmpty(message = "Each Product option must be non-empty") String> optionIds,

        List<RequestNutritionalCreated> nutritional
) {
}

