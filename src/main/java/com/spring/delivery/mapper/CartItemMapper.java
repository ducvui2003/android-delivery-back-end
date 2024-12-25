package com.spring.delivery.mapper;

import com.spring.delivery.domain.request.cart.RequestCartCreated;
import com.spring.delivery.domain.response.cart.ResponseCart;
import com.spring.delivery.domain.response.product.ProductDTO;
import com.spring.delivery.domain.response.product.ProductOptionDTO;
import com.spring.delivery.model.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CartItemMapper {
    CartItem toCartItem(RequestCartCreated request);

    @Mapping(target = "id", source = "cartItem.id")
    @Mapping(target = "productId", source = "cartItem.productId")
    @Mapping(target = "quantity", source = "cartItem.quantity")
    ResponseCart toResponseCart(CartItem cartItem);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "price", source = "price")
    ResponseCart.Option toOption(ProductOptionDTO optionDTO);

    default ResponseCart toResponseCart(ResponseCart responseCart, ProductDTO productDTO) {
        responseCart.setName(productDTO.getName());
        responseCart.setPrice(productDTO.getPrice());
        responseCart.setThumbnail(productDTO.getImage());
        List<ResponseCart.Option> options = productDTO.getOptions().stream().map(this::toOption).toList();
        responseCart.setOptions(options);

        return responseCart;
    }
}
