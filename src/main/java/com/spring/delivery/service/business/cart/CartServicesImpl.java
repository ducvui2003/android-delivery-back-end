package com.spring.delivery.service.business.cart;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.delivery.domain.request.cart.RequestCartCreated;
import com.spring.delivery.domain.response.cart.ResponseCart;
import com.spring.delivery.domain.response.product.ProductDTO;
import com.spring.delivery.mapper.CartItemMapper;
import com.spring.delivery.model.Cart;
import com.spring.delivery.model.CartItem;
import com.spring.delivery.repository.mysql.ICartItemRepository;
import com.spring.delivery.repository.mysql.ICartRepository;
import com.spring.delivery.repository.mysql.UserRepository;
import com.spring.delivery.service.business.product.IProductService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
public class CartServicesImpl implements ICartServices {
    ICartRepository cartRepository;
    CartItemMapper cartItemMapper;
    UserRepository userRepository;
    ICartItemRepository cartItemRepository;
    IProductService productService;
    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public ResponseCart addCartItem(Long userId, RequestCartCreated request) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseGet(() -> createCart(userId));
        boolean productExist = isProductExist(request.productId(), request.optionIds());
        if (!productExist)
            return null;
        CartItem cartItemSaved = createCartItem(cart, request);
        return cartItemMapper.toResponseCart(cartItemSaved);
    }

    /**
     * Tạo giỏ hàng mới cho user nếu chưa tồn tại
     */
    private Cart createCart(Long userId) {
        Cart newCart = new Cart();
        userRepository.findById(userId)
                .ifPresent(newCart::setUser);
        return cartRepository.save(newCart);
    }

    /**
     * Kiểm tra sản phẩm có tồn tại không
     */
    private boolean isProductExist(String productId, List<String> optionIds) {
        ProductDTO productDTO = productService.findById(productId);
        if (productDTO == null) {
            return false;
        }
        return productDTO.getOptions().stream()
                .anyMatch(productOptionDTO -> optionIds.contains(productOptionDTO.id()));
    }

    /**
     * Tạo item giỏ hàng mới hoặc cập nhật số lượng nếu đã tồn tại
     */
    private CartItem createCartItem(Cart cart, RequestCartCreated request) {
        Optional<CartItem> cartItemOptional = null;
        try {
            cartItemOptional = cartItemRepository.findByCartIdAndProductIdAndOptionIds(
                    cart.getId(), request.productId(), objectMapper.writeValueAsString(request.optionIds()));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        CartItem cartItem;
        if (cartItemOptional.isPresent()) {
            cartItem = cartItemOptional.get();
            cartItem.setQuantity(cartItem.getQuantity() + request.quantity());
        } else {
            cartItem = cartItemMapper.toCartItem(request);
            cartItem.setCart(cart);
        }
        return cartItemRepository.save(cartItem);
    }


    @Override
    public ResponseCart getAllCartItem(Long userId) {
        return null;
    }

    @Override
    public ResponseCart getCartItem(Long userId, Long cartItemId) {
        return null;
    }

    @Override
    public ResponseCart deleteCartItem(Long userId, Long cartItemId) {
        return null;
    }
}
