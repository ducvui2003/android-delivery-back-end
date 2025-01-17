package com.spring.delivery.mapper;

import com.spring.delivery.domain.request.order.RequestOrderCreated;
import com.spring.delivery.domain.response.order.ResponseOrderDetail;
import com.spring.delivery.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderMapper {
    Order toOrder(RequestOrderCreated cartCreated);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "address", target = "address")
    @Mapping(source = "paymentMethod", target = "paymentMethod")
    @Mapping(source = "shippingFee", target = "deliveryFee")
    @Mapping(source = "subTotal", target = "subTotal")
    @Mapping(source = "orderItems", target = "items")
    ResponseOrderDetail toResponseOrderDetail(Order order);
}
