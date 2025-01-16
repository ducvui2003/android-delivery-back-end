package com.spring.delivery.domain.request.order;

import com.spring.delivery.util.enums.StatusOrder;

public record RequestUpdateOrder(
        StatusOrder status

) {
}
