package com.spring.delivery.service.order.impl;

import com.spring.delivery.domain.request.RequestOrderCreated;
import com.spring.delivery.domain.response.order.OrderDTO;
import com.spring.delivery.mapper.OrderMapper;
import com.spring.delivery.model.Order;
import com.spring.delivery.repository.mysql.OrderRepository;
import com.spring.delivery.service.order.OrderService;
import com.spring.delivery.util.SecurityUtil;
import com.spring.delivery.util.enums.StatusOrder;
import com.spring.delivery.util.exception.AppErrorCode;
import com.spring.delivery.util.exception.AppException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class OrderServiceImpl implements OrderService {
    OrderRepository orderRepository;
    OrderMapper orderMapper;
    SecurityUtil securityUtil;

    @Override
    public OrderDTO addOrder(RequestOrderCreated req) {
        Long id = securityUtil.getCurrentUserDTOFromAccessToken().orElse(null).id();
        Order order = orderMapper.toOrder(req);
        order.setStatus(StatusOrder.ACTIVE);
        order.setUserId(id);
        return orderMapper.toOrderDetailDTO(orderRepository.save(order));
    }

    @Override
    public List<OrderDTO> getOrders(int page, int size, String sortBy) {
        var pageable = PageRequest.of(page, size, Sort.by(sortBy).descending());
        Page<Order> orders = orderRepository.findAll(pageable);
        return orderMapper.toOrderDTOs(orders.stream().toList());
    }

    @Override
    public Integer updateOrderStatus(Long id, String status) {
        var order = orderRepository.findById(id).orElseThrow(() -> new AppException(AppErrorCode.ORDER_NOT_FOUND));
        return orderRepository.updateOrderStatus(id, StatusOrder.valueOf(status));
    }
}
