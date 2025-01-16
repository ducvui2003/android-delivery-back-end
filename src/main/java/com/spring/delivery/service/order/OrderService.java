package com.spring.delivery.service.order;

import com.spring.delivery.domain.request.order.RequestOrderCreated;
import com.spring.delivery.domain.response.order.ResponseOrder;
import com.spring.delivery.domain.response.order.ResponseOrderDetail;
import com.spring.delivery.util.enums.StatusOrder;

import java.util.List;

public interface OrderService {
    ResponseOrder createOrder(Long userId, RequestOrderCreated req);

    ResponseOrderDetail getOrder(Long orderId);

    List<ResponseOrder> getOrders(int page, int size, String sortBy);

    boolean updateOrderStatus(Long id, StatusOrder status);

    List<ResponseOrder> getOrdersByStarReviewOrStatus(Integer starReview, StatusOrder statusOrder, int page, int size);
}
