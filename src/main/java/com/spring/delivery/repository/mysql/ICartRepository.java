package com.spring.delivery.repository.mysql;

import com.spring.delivery.model.Cart;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUserId(Long userId);

    @Modifying
    @Query(value = "INSERT INTO Cart (user_id) VALUES (:userId) RETURNING id", nativeQuery = true)
    Optional<Long> saveWithUserId(@Param("userId") Long userId);
}
