package com.spring.delivery.repository.mysql;

import com.spring.delivery.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    OrderItem findByOrderId(Long orderDetailId);
}
