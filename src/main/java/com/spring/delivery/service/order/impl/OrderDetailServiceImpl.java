package com.spring.delivery.service.order.impl;

import com.spring.delivery.domain.request.order.RequestOrderDetailCreated;
import com.spring.delivery.domain.response.order.ResponseOrderDetail;
import com.spring.delivery.mapper.OrderDetailMapper;
import com.spring.delivery.model.Order;
import com.spring.delivery.repository.mysql.OrderItemRepository;
import com.spring.delivery.repository.mysql.OrderRepository;
import com.spring.delivery.service.order.OrderDetailService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderDetailServiceImpl implements OrderDetailService {
    OrderDetailMapper orderDetailMapper;
    OrderItemRepository orderItemRepository;
    OrderRepository orderRepository;


    @Override
    public ResponseOrderDetail getOrderDetailById(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isEmpty()) {
            return null;
        }
        return null;
    }

    @Override
    public RequestOrderDetailCreated addOrderDetail(RequestOrderDetailCreated req) {
        log.info("order detail  ");
        var orderDetail = orderItemRepository.save(orderDetailMapper.toOrderDetail(req));
        log.info("order detail: {} ", orderDetail);
        return orderDetailMapper.toOrderDetailCreated(orderDetail);
    }
}
