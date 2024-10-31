package com.spring.delivery.domain.request.product;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;

public record RequestDiscountCreated(
        @NotNull(message = "Discount is required")
        Double discount,
        @NotNull(message = "Expired datetime is required")
        @Future(message = "Expiration date must be in the future")
        Instant expired
) {
}
