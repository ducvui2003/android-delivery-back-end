package com.spring.delivery.service.order;

import com.spring.delivery.domain.request.order.RequestOrderDetailCreated;
import com.spring.delivery.domain.response.order.ResponseOrderDetail;

public interface OrderDetailService {

    ResponseOrderDetail getOrderDetailById(Long id);

    RequestOrderDetailCreated addOrderDetail(RequestOrderDetailCreated req);
}
