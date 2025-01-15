package com.spring.delivery.mapper;

import com.spring.delivery.domain.request.order.RequestOrderDetailCreated;
import com.spring.delivery.domain.response.order.OrderDetailDTO;
import com.spring.delivery.domain.response.order.OrderDetailResponse;
import com.spring.delivery.model.Order;
import com.spring.delivery.model.OrderDetail;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderDetailMapper {

    OrderDetailDTO toOrderDetailDTO(OrderDetail orderDetail);

    OrderDetailResponse toOrderDetailResponse(OrderDetail orderDetail);

    OrderDetail toOrderDetail(RequestOrderDetailCreated req);

    RequestOrderDetailCreated toOrderDetailCreated(OrderDetail orderDetail);

}
