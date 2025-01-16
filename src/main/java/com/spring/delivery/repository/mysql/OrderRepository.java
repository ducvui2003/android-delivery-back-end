package com.spring.delivery.repository.mysql;

import com.spring.delivery.model.Order;
import com.spring.delivery.util.enums.StatusOrder;
import io.lettuce.core.dynamic.annotation.Param;
import org.jetbrains.annotations.NotNull;
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
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Modifying
    @Query("UPDATE Order  o SET o.status = :status WHERE o.id = :id")
    Integer updateOrderStatus(@Param("id") Long id, @Param("status") StatusOrder status);


    Page<Order> findAll(Pageable pageable);

    @Query("""
                SELECT o FROM Order o 
                WHERE (:starReview IS NULL OR o.starReview = :starReview)
                  AND (:statusOrder IS NULL OR o.status = :statusOrder) 
                  AND o.userId = :userId
            """)
    Page<Order> findOrdersByOptionalFields(
            @Param("userId") Long userId,
            @Param("starReview") Integer starReview,
            @Param("statusOrder") StatusOrder statusOrder,
            Pageable pageable
    );

    boolean existsById(@NotNull Long id);
}
