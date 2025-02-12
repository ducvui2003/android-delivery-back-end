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
import feign.Response;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        ResponseCart cartItemSaved = createCartItem(cart, request);
        return cartItemSaved;
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
    private ResponseCart createCartItem(Cart cart, RequestCartCreated request) {
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
        cartItemRepository.save(cartItem);

        // Mapper
        return toResponseCart(cartItem);
    }

    private ResponseCart toResponseCart(CartItem cartItem) {
        ResponseCart responseCart = cartItemMapper.toResponseCart(cartItem);
        ProductDTO productDTO = productService.findById(responseCart.getProductId());
        return cartItemMapper.toResponseCart(responseCart, productDTO);
    }

    @Override
    public List<ResponseCart> getAllCartItem(Long userId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseGet(() -> createCart(userId));
        List<CartItem> cartItems = cartItemRepository.findCartItemByCartId(cart.getId());
        List<ResponseCart> response = new ArrayList<>();
        for (CartItem cartItem : cartItems) {
            response.add(toResponseCart(cartItem));
        }
        return response;
    }

    @Override
    public ResponseCart getCartItem(Long userId, Long cartItemId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseGet(() -> createCart(userId));
        Optional<CartItem> cartItemOptional = cartItemRepository.findCartItemByIdAndCartId(cartItemId, cart.getId());
        return cartItemOptional.map(this::toResponseCart).orElse(null);
    }

    @Override
    public boolean deleteCartItem(Long userId, Long cartItemId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseGet(() -> createCart(userId));
        Optional<CartItem> cartItemOptional = cartItemRepository.findCartItemByIdAndCartId(cartItemId, cart.getId());
        if (cartItemOptional.isEmpty()) return false;
        cartItemRepository.delete(cartItemOptional.get());
        return true;
    }

    @Override
    public boolean increaseQuantity(Long userId, Long cartItemId, Integer quantity) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseGet(() -> createCart(userId));
        Optional<CartItem> cartItemOptional = cartItemRepository.findCartItemByIdAndCartId(cartItemId, cart.getId());
        if (cartItemOptional.isEmpty()) return false;
        CartItem cartItem = cartItemOptional.get();
        cartItem.setQuantity(cartItem.getQuantity() + quantity);
        cartItemRepository.save(cartItem);
        return true;
    }

    @Override
    public boolean decreaseQuantity(Long userId, Long cartItemId, Integer quantity) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseGet(() -> createCart(userId));
        Optional<CartItem> cartItemOptional = cartItemRepository.findCartItemByIdAndCartId(cartItemId, cart.getId());
        if (cartItemOptional.isEmpty()) return false;
        CartItem cartItem = cartItemOptional.get();
        if (cartItemOptional.get().getQuantity() < quantity) {
            return false;
        }
        cartItem.setQuantity(cartItem.getQuantity() - quantity);
        cartItemRepository.save(cartItem);
        return true;
    }
}
