package com.spring.delivery.mapper;

import com.spring.delivery.domain.response.order.OrderDTO;
import com.spring.delivery.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderMapper {
    OrderDTO toOrderDetailDTO(Order order);

    Order toOrder(OrderDTO orderDTO);
}
