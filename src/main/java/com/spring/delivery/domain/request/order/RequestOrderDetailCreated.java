package com.spring.delivery.domain.request.order;

import com.spring.delivery.domain.response.product.ProductDTO;
import com.spring.delivery.domain.response.promotion.PromotionDTO;
import com.spring.delivery.domain.response.promotion.PromotionOrderResponse;
import com.spring.delivery.model.Order;
import com.spring.delivery.util.enums.PaymentMethod;

import java.util.List;
import java.util.Set;

public record RequestOrderDetailCreated(
        List<String> productIds,
        String address,
        PaymentMethod parameterModel,
        Set<String> promotionIds,
        Double subTotal,
        Double deliveryFee,
        Double discount,
        String description,
        String reasonForCancellation
) {
}
