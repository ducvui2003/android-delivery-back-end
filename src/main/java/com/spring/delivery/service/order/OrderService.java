package com.spring.delivery.service.order;

import com.spring.delivery.domain.request.order.RequestOrderCreated;
import com.spring.delivery.domain.response.order.ResponseOrder;
import com.spring.delivery.domain.response.order.ResponseOrderDetail;
import com.spring.delivery.util.enums.StatusOrder;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {
    ResponseOrder createOrder(Long userId, RequestOrderCreated req);

    ResponseOrderDetail getOrderDetail(Long orderId);

    List<ResponseOrder> getOrders(
            Long userId,
            Integer starReview,
            StatusOrder statusOrder,
            Pageable pageable);

    List<ResponseOrder> getOrders(Pageable pageable);


    boolean updateOrderStatus(Long id, StatusOrder status);


}
