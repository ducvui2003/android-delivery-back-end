package com.spring.delivery.domain.request.product;

import jakarta.validation.constraints.NotNull;

public record RequestUpdateImage(
        @NotNull(message = "Image URL is required") String url
) {
}
