package com.spring.delivery.domain.request.product;


import jakarta.validation.constraints.NotBlank;

public record RequestCategoryCreated(
        @NotBlank String id
) {
}
