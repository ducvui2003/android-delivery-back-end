package com.spring.delivery.domain.response.promotion;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.util.List;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record PromotionBaseDTO(
        String id,

        List<Long> userIds,

        String name
) {
}
