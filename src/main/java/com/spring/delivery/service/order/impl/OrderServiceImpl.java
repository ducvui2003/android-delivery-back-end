package com.spring.delivery.service.order.impl;

import com.spring.delivery.document.DiscountInfo;
import com.spring.delivery.domain.request.order.RequestOrderCreated;
import com.spring.delivery.domain.response.order.ResponseOrder;
import com.spring.delivery.domain.response.order.ResponseOrderDetail;
import com.spring.delivery.domain.response.product.ProductDTO;
import com.spring.delivery.domain.response.product.ProductOptionDTO;
import com.spring.delivery.domain.response.promotion.PromotionDTO;
import com.spring.delivery.mapper.OrderMapper;
import com.spring.delivery.model.*;
import com.spring.delivery.repository.mysql.ICartItemRepository;
import com.spring.delivery.repository.mysql.OrderRepository;
import com.spring.delivery.repository.mysql.UserRepository;
import com.spring.delivery.service.business.product.IProductService;
import com.spring.delivery.service.order.OrderService;
import com.spring.delivery.service.promotion.PromotionService;
import com.spring.delivery.util.enums.StatusOrder;
import com.spring.delivery.util.exception.AppErrorCode;
import com.spring.delivery.util.exception.AppException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class OrderServiceImpl implements OrderService {
    OrderRepository orderRepository;
    OrderMapper orderMapper;
    IProductService productService;
    ICartItemRepository cartItemRepository;
    PromotionService promotionService;
    UserRepository userRepository;

    @Override
    public ResponseOrderDetail getOrder(Long orderId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        return optionalOrder.map(orderMapper::toResponseOrderDetail).orElse(null);
    }

    @Transactional
    @Override
    public ResponseOrder createOrder(Long userId, RequestOrderCreated req) {
        List<OrderItem> orderItems = getOrderItems(req.cartItemIds());
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty())
            throw new AppException(AppErrorCode.USER_NOT_FOUND);
        User user = optionalUser.get();

        if (orderItems == null)
            throw new AppException(AppErrorCode.CART_ITEMS_NOT_FOUND);

        double orderItemPrice = orderItems.stream().map(OrderItem::getPrice).reduce(0.0, Double::sum);
        OrderPromotion promotionShip = getOrderPromotion(req.promotionShipId());
        OrderPromotion promotionProduct = getOrderPromotion(req.promotionProductId());

        Order order = orderMapper.toOrder(req);
        order.setStatus(StatusOrder.ACTIVE);
        order.setPaymentMethod(req.paymentMethod());
        order.setSubTotal(orderItemPrice);

        order.setUserId(userId);
        order.setEmail(user.getEmail());
        order.setPhone(user.getPhoneNumber());
        order.setFullName(user.getFullName());
        order.setAddress(req.address());

        if (promotionShip != null)
            order.setPromotionShip(promotionShip);
        if (promotionProduct != null)
            order.setPromotionProduct(promotionProduct);

        for (OrderItem item : orderItems) {
            item.setOrder(order);
        }

        order.setOrderItems(new HashSet<>(orderItems));

        Order orderSaved = orderRepository.save(order);

//        Delete cart items
        cartItemRepository.deleteAllByIdInBatch(req.cartItemIds());

        return toResponseOrder(orderSaved);
    }

    private List<OrderItem> getOrderItems(List<Long> cartItemsId) {
        List<CartItem> cartItems = cartItemRepository.findCartItemByIdIn(cartItemsId);
        List<String> productIds = cartItems.stream().map(CartItem::getProductId).collect(Collectors.toList());
        List<ProductDTO> productDTOS = productService.findByIdIn(productIds);

        List<OrderItem> result = new ArrayList<>();
        for (int i = 0; i < productDTOS.size(); i++) {
            ProductDTO item = productDTOS.get(i);
            DiscountInfo discountInfo = item.getDiscountInfo();
            List<String> optionIds = cartItems.get(i).getOptionIds();

            List<OrderItemOption> orderItemOptions = new ArrayList<>();
            for (ProductOptionDTO option : item.getOptions()) {
                if (optionIds.contains(option.id()))
                    orderItemOptions.add(OrderItemOption.builder()
                            .id(option.id())
                            .name(option.name())
                            .price(option.price())
                            .build());
                else if (option.options() != null)
                    for (ProductOptionDTO optionInner : option.options()) {
                        if (optionIds.contains(optionInner.id()))
                            orderItemOptions.add(OrderItemOption.builder()
                                    .id(optionInner.id())
                                    .name(optionInner.name())
                                    .price(optionInner.price())
                                    .build());
                    }
            }


            double price = discountInfo != null ?
                    discountInfo.getDiscount() * item.getPrice()
                    : item.getPrice();

            OrderItem orderItem = OrderItem.builder()
                    .productId(item.getId())
                    .name(item.getName())
                    .category(item.getCategory().name())
                    .price(price)
                    .thumbnail(item.getImage())
                    .options(orderItemOptions)
                    .build();

            result.add(orderItem);
        }

        if (result.isEmpty())
            return null;
        return result;
    }


    private OrderPromotion getOrderPromotion(String promotionId) {
        PromotionDTO promotion = promotionService.getPromotion(promotionId);

        if (promotion != null) {
            return OrderPromotion.builder()
                    .id(promotion.id())
                    .code(promotion.promotionCode())
                    .name(promotion.name())
                    .discount(promotion.discountPromotionInfo().getDiscount())
                    .build();
        }
        return null;
    }

    @Override
    public List<ResponseOrder> getOrders(int page, int size, String sortBy) {
        var pageable = PageRequest.of(page, size, Sort.by(sortBy).descending());
        Page<Order> orders = orderRepository.findAll(pageable);
        if (orders.getContent().isEmpty())
            return null;
        return orders.getContent().stream().map(this::toResponseOrder).collect(Collectors.toList());
    }

    @Override
    public Integer updateOrderStatus(Long id, String status) {
        var order = orderRepository.findById(id).orElseThrow(() -> new AppException(AppErrorCode.ORDER_NOT_FOUND));
        return orderRepository.updateOrderStatus(id, StatusOrder.valueOf(status));
    }

    @Override
    public List<ResponseOrder> getOrdersByStarReviewOrStatus(Integer starReview, StatusOrder statusOrder, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        return orderMapper.toOrderDTOs(
                orderRepository.getOrdersByStarReviewOrStatus(starReview, statusOrder, pageable)
                        .stream().toList());
    }

    ResponseOrder toResponseOrder(Order order) {
        List<String> images = order.getOrderItems().stream().map(OrderItem::getThumbnail).toList();
        return new ResponseOrder(
                order.getId(),
                order.getSubTotal(),
                images,
                order.getStarReview(),
                order.getStatus()
        );
    }
}
