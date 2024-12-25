package com.spring.delivery.service.business.cart;

import com.spring.delivery.domain.request.cart.RequestCartCreated;
import com.spring.delivery.domain.response.cart.ResponseCart;

import java.util.List;

public interface ICartServices {

    public ResponseCart addCartItem(
            Long userId,
            RequestCartCreated request);

    public List<ResponseCart> getAllCartItem(Long userId);

    public ResponseCart getCartItem(Long userId, Long cartItemId);

    public boolean increaseQuantity(Long userId, Long cartItemId, Integer quantity);

    public boolean decreaseQuantity(Long userId, Long cartItemId, Integer quantity);

    public boolean deleteCartItem(Long userId, Long cartItemId);


}
