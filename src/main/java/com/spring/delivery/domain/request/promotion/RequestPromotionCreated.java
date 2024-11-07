package com.spring.delivery.domain.request.promotion;

import com.spring.delivery.document.DiscountPromotionInfo;
import com.spring.delivery.util.enums.converter.PromotionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RequestPromotionCreated(
        @NotNull(message = "userId must be not null")
        Long userId,

        @NotBlank(message = "name promotion not empty")
        String name,

        @NotBlank(message = "name promotion not empty")
        String description,

        @NotBlank(message = "promotion code promotion not empty")
        String promotionCode,

        @NotBlank(message = "applicable scope promotion not empty")
        String applicableScope,

        @NotBlank(message = "terms and conditions promotion not empty")
        String termsAndConditions,

        @NotNull(message = "discount must be not null")
        DiscountPromotionInfo discountPromotionInfo,

        @NotNull(message = "type promotion must be not null")
        PromotionType type
) {
}
