package com.spring.delivery.service.order;

import com.spring.delivery.domain.response.order.OrderDetailDTO;
import com.spring.delivery.domain.response.order.OrderDetailResponse;

import java.util.Set;

public interface OrderDetailService {

    OrderDetailResponse getOrderDetailById(Long id);
}
