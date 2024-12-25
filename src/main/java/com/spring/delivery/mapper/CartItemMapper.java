package com.spring.delivery.mapper;

import com.spring.delivery.domain.request.cart.RequestCartCreated;
import com.spring.delivery.domain.response.cart.ResponseCart;
import com.spring.delivery.model.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartItemMapper {
    CartItem toCartItem(RequestCartCreated request);

    @Mapping(target = "id", source = "cartItem.cart.id")
    @Mapping(target = "productId", source = "cartItem.productId")
    @Mapping(target = "optionIds", source = "cartItem.optionIds")
    @Mapping(target = "quantity", source = "cartItem.quantity")
    ResponseCart toResponseCart(CartItem cartItem);
}
