package com.spring.delivery.domain.response.promotion;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.spring.delivery.document.DiscountPromotionInfo;
import com.spring.delivery.util.enums.converter.PromotionType;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record PromotionDTO(

        String id,

        List<Long> userIds,

        String name,

        String description,

        String promotionCode,

        String applicableScope,

        String termsAndConditions,

        DiscountPromotionInfo discountPromotionInfo,

        PromotionType type
) {
}
