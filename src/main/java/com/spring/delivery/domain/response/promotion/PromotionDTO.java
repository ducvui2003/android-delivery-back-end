package com.spring.delivery.domain.response.promotion;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record PromotionDTO(
        String name,

        String description,

        String promotionCode,

        String applicableScope,

        double discountAmount,

        String termsAndConditions

        ) {
}
