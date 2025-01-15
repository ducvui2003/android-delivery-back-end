package com.spring.delivery.mapper;

import com.spring.delivery.domain.request.cart.RequestCartCreated;
import com.spring.delivery.domain.response.cart.ResponseCart;
import com.spring.delivery.domain.response.product.ProductOptionDTO;
import com.spring.delivery.model.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartItemMapper {
    CartItem toCartItem(RequestCartCreated request);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "price", source = "price")
    ResponseCart.Option toOption(ProductOptionDTO optionDTO);

}
