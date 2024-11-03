package com.spring.delivery.domain.request.product;

import com.spring.delivery.util.enums.Unit;
import jakarta.validation.constraints.NotNull;

public record RequestNutritionalCreated(
        @NotNull(message = "Name is required")
        String name,
        @NotNull(message = "Value is required")
        Double value,
        @NotNull(message = "Unit is required")
        Unit unit
) {
}