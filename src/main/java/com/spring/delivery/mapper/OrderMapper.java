package com.spring.delivery.mapper;

import com.spring.delivery.domain.request.order.RequestOrderCreated;
import com.spring.delivery.domain.response.order.OrderDTO;
import com.spring.delivery.model.Order;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderDTO toOrderDetailDTO(Order order);

    List<OrderDTO> toOrderDTOs(List<Order> orders);

    Order toOrder(RequestOrderCreated orderDTO);
}
