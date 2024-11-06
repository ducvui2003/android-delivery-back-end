package com.spring.delivery.domain.request.promotion;

import com.spring.delivery.document.DiscountPromotionInfo;
import com.spring.delivery.util.enums.converter.PromotionType;

public record RequestPromotionCreated(
        Long userId,

        String name,

        String description,

        String promotionCode,

        String applicableScope,

        String termsAndConditions,

        DiscountPromotionInfo discountPromotionInfo,

        PromotionType type
) {
}
