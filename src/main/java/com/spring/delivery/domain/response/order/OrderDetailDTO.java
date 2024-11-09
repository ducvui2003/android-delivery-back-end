package com.spring.delivery.domain.response.order;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.spring.delivery.domain.response.product.ProductDTO;
import com.spring.delivery.domain.response.promotion.PromotionDTO;
import com.spring.delivery.util.enums.PaymentMethod;
import lombok.Builder;

import java.util.List;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public record OrderDetailDTO(
        List<ProductDTO> productDTOList,
        String address,
        PaymentMethod paymentMethod,
        Set<PromotionDTO> promotionDTOS,
        Double subTotal,
        Double deliveryFee,
        Double discount,
        String description,
        String reasonForCancellation

) {
}
