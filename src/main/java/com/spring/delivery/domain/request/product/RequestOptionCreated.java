package com.spring.delivery.domain.request.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

public record RequestOptionCreated(
        @NotNull(message = "Name is required") String name,
        @Positive(message = "Price must be a positive number") Double price,
        List<@NotNull(message = "Each option must not be null") RequestOptionCreated> options
) {
    boolean isValidate() {
        return price != null || (options != null && !options.isEmpty());
    }
}