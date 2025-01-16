package com.spring.delivery.domain.request.order;

import com.spring.delivery.util.enums.PaymentMethod;

import java.util.List;

public record RequestOrderCreated(
        PaymentMethod paymentMethod,
        String promotionShipId,
        String promotionProductId,
        List<Long> cartItemIds,
        String address
) {
}
