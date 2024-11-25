package com.spring.delivery.mapper;

import com.spring.delivery.domain.response.order.OrderDTO;
import com.spring.delivery.domain.response.order.OrderDetailDTO;
import com.spring.delivery.model.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderDTO toOrderDetailDTO(Order order);

    Order toOrder(OrderDTO orderDTO);
}
