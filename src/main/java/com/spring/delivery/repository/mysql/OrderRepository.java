package com.spring.delivery.repository.mysql;

import com.spring.delivery.model.Order;
import com.spring.delivery.util.enums.StatusOrder;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Modifying
    @Query("UPDATE Order  o SET o.status = :status WHERE o.id = :id")
    @Transactional
    Integer updateOrderStatus(@Param("id") Long id, @Param("status") StatusOrder status);
}
