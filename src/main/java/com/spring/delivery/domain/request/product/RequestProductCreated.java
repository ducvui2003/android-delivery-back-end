package com.spring.delivery.domain.request.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record RequestProductCreated(
        @NotBlank(message = "Name not blank")
        String name,

        @Min(value = 0, message = "Price must higher than 0")
        Double price,

        @Min(value = 0, message = "Quantity must higher than 0")
        Integer quantity,

        @NotBlank(message = "Description not blank")
        String description,

        @NotBlank(message = "Category Id not blank")
        @JsonProperty("category")
        String categoryId,

        @NotNull(message = "Product option Id is required")
        @NotEmpty(message = "Product option Id cannot be empty")
        @JsonProperty("options")
        List<@NotEmpty(message = "Each Product option must be non-empty") String> optionIds,

        List<RequestNutritionalCreated> nutritional,

        String image
) {
}

