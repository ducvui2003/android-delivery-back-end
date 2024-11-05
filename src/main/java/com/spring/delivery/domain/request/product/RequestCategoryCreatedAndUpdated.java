package com.spring.delivery.domain.request.product;


import jakarta.validation.constraints.NotBlank;

public record RequestCategoryCreatedAndUpdated(
        @NotBlank(message = "Name category must not blank") String name,
        @NotBlank(message = "Url image (urlImage) category must not blank") String urlImage
) {
}
