package com.spring.delivery.domain.request.product;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

public record RequestOptionGroupCreated(
        @NotNull(message = "Name is required") String name,
        @NotEmpty(message = "Options cannot be empty") List<@NotNull(message = "Each option must not be null") RequestOptionCreated> options
) {
    public record RequestOptionCreated(
            @NotNull(message = "Name is required") String name,
            @NotNull(message = "Price is required") @Positive(message = "Price must be a positive number") Double price
    ) {
    }
}
