package com.spring.delivery.mapper;

import com.spring.delivery.domain.request.order.RequestOrderCreated;
import com.spring.delivery.domain.response.order.ResponseOrder;
import com.spring.delivery.domain.response.order.ResponseOrderDetail;
import com.spring.delivery.model.Order;
import com.spring.delivery.model.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    ResponseOrder toResponseOrder(Order order);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "address", target = "address")
    @Mapping(source = "paymentMethod", target = "paymentMethod")
    @Mapping(source = "shippingFee", target = "deliveryFee")
    @Mapping(source = "subTotal", target = "subTotal")
    @Mapping(source = "orderItems", target = "items")
    ResponseOrderDetail toResponseOrderDetail(Order order);


    Order toOrder(RequestOrderCreated orderDTO);
}
