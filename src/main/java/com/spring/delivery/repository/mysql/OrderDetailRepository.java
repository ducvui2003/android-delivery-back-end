package com.spring.delivery.repository.mysql;

import com.spring.delivery.model.Order;
import com.spring.delivery.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    OrderDetail findByOrderId(Long orderDetailId);
}
