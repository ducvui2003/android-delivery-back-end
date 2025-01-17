package com.spring.delivery.repository.mysql;

import com.spring.delivery.model.CartItem;
import feign.Param;
import org.springframework.data.domain.Limit;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface ICartItemRepository extends JpaRepository<CartItem, Long> {

    @Query(value = """
            SELECT * 
            FROM cart_items 
            WHERE cart_id = :cartId 
              AND product_id = :productId 
              AND option_ids = CAST(:optionIds AS JSON)
            """, nativeQuery = true)
    Optional<CartItem> findByCartIdAndProductIdAndOptionIds(
            @Param("cartId") long cartId,
            @Param("productId") String productId,
            @Param("optionIds") String optionIds
    );

    Optional<CartItem> findByCart_IdAndProductId(
            long cartId,
            String productId
    );


    List<CartItem> findCartItemByCartId(long cartId);

    Optional<CartItem> findCartItemByIdAndCartId(long cartItemId, long cartId);

    public void deleteByCartId(long cartId);

    List<CartItem> findCartItemByIdIn(List<Long> cartItemsId);
}
