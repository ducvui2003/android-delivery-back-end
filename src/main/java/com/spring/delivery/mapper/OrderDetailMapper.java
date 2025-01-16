package com.spring.delivery.mapper;

import com.spring.delivery.domain.response.order.OrderDetailDTO;
import com.spring.delivery.domain.response.order.OrderDetailResponse;
import com.spring.delivery.model.OrderDetail;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderDetailMapper {

    OrderDetailDTO toOrderDetailDTO(OrderDetail orderDetail);

    OrderDetail toOrderDetail(OrderDetailDTO orderDetailDTO);

    OrderDetailResponse toOrderDetailResponse(OrderDetail orderDetail);


}
