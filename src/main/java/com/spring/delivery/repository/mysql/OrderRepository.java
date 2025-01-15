package com.spring.delivery.repository.mysql;

import com.spring.delivery.model.Order;
import com.spring.delivery.util.enums.StatusOrder;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.Limit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Modifying
    @Query("UPDATE Order  o SET o.status = :status WHERE o.id = :id")
    @Transactional
    Integer updateOrderStatus(@Param("id") Long id, @Param("status") StatusOrder status);

//    @Query("SELECT o FROM Order o WHERE o.starReview = :starReview OR o.status = :status ORDER BY o.createdAt DESC")
    Page<Order> getOrdersByStarReviewOrStatus(Integer starReview, StatusOrder status, Pageable pageable);
}
