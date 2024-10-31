package com.spring.delivery.domain.request.product;

import com.spring.delivery.util.enums.Unit;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record RequestProductCreated(
        String name,
        Double price,
        Integer quantity,
        String description,
        String category,
        RequestDiscountCreated discountInfo,
        @NotNull(message = "Product option Id is required")
        @NotEmpty(message = "Product option Id cannot be empty")
        List<@NotEmpty(message = "Each Product option must be non-empty") String> options,
        RequestNutritionalCreated nutritional
) {

    public record RequestNutritionalCreated(
            @NotNull(message = "Name is required")
            String name,
            @NotNull(message = "Value is required")
            Double value,
            @NotNull(message = "Unit is required")
            Unit unit
    ) {
    }
}
