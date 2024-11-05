package com.spring.delivery.domain.request.product;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record RequestProductUpdated(
        String name,

        Double price,

        Integer quantity,

        String description,

        String categoryId,

        List<@NotEmpty(message = "Each Product option must be non-empty") String> optionIds,

        List<RequestNutritionalCreated> nutritional
) {
}

