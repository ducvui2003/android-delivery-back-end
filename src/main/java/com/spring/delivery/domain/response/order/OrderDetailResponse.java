package com.spring.delivery.domain.response.order;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.spring.delivery.domain.response.product.ProductDTO;
import com.spring.delivery.domain.response.promotion.PromotionDTO;
import com.spring.delivery.domain.response.promotion.PromotionOrderResponse;
import com.spring.delivery.util.enums.PaymentMethod;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDetailResponse {
    Long id;
    List<ProductDTO> productDTOList;
    String address;
    PaymentMethod paymentMethod;
    Set<PromotionOrderResponse> promotions;
    Double subTotal;
    Double deliveryFee;
    Double discount;
    String description;
    String reasonForCancellation;
}
