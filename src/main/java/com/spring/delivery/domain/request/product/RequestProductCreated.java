package com.spring.delivery.domain.request.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record RequestProductCreated(
        String name,

        Double price,

        Integer quantity,

        String description,

        String categoryId,

        @NotNull(message = "Product option Id is required")
        @NotEmpty(message = "Product option Id cannot be empty")
        @JsonProperty("options")
        List<@NotEmpty(message = "Each Product option must be non-empty") String> optionIds,

        List<RequestNutritionalCreated> nutritional
) {
}

