package com.spring.delivery.service.business.cart;

import com.spring.delivery.domain.request.cart.RequestCartCreated;
import com.spring.delivery.domain.response.cart.ResponseCart;

public interface ICartServices {

    public ResponseCart addCartItem(
            Long userId,
            RequestCartCreated request);

    public ResponseCart getAllCartItem(Long userId);

    public ResponseCart getCartItem(Long userId, Long cartItemId);

    public ResponseCart deleteCartItem(Long userId, Long cartItemId);


}
