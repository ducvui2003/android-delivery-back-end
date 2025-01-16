package com.spring.delivery.mapper;

import com.spring.delivery.domain.request.order.RequestOrderDetailCreated;
import com.spring.delivery.domain.response.order.OrderItemDTO;
import com.spring.delivery.domain.response.order.ResponseOrderDetail;
import com.spring.delivery.model.OrderItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderDetailMapper {

    OrderItemDTO toOrderDetailDTO(OrderItem orderItem);

    ResponseOrderDetail toOrderDetailResponse(OrderItem orderItem);

    OrderItem toOrderDetail(RequestOrderDetailCreated req);

    RequestOrderDetailCreated toOrderDetailCreated(OrderItem orderItem);

}
