package com.spring.delivery.service.order;

import com.spring.delivery.domain.request.order.RequestOrderDetailCreated;
import com.spring.delivery.domain.response.order.OrderDetailDTO;
import com.spring.delivery.domain.response.order.OrderDetailResponse;
import com.spring.delivery.model.OrderDetail;

import java.util.Set;

public interface OrderDetailService {

    OrderDetailResponse getOrderDetailById(Long id);

    RequestOrderDetailCreated addOrderDetail(RequestOrderDetailCreated req);
}
