package com.spring.delivery.service.order;

import com.google.api.Page;
import com.spring.delivery.domain.request.RequestOrderCreated;
import com.spring.delivery.domain.response.order.OrderDTO;
import com.spring.delivery.domain.response.order.OrderDetailDTO;
import com.spring.delivery.model.Order;
import com.spring.delivery.util.enums.StatusOrder;

import java.util.List;

public interface OrderService {
    OrderDTO addOrder(RequestOrderCreated req);

    List<OrderDTO> getOrders(int page, int size, String sortBy);

    Integer updateOrderStatus(Long id, String status);

}
