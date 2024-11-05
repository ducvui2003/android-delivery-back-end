package com.spring.delivery.repository.mysql;

import com.spring.delivery.model.UserProductFavorite;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserProductFavoriteRepository extends JpaRepository<UserProductFavorite, Long> {
    List<UserProductFavorite> findByUser_IdAndProductIdIsIn(Long id, List<String> productIds);

    boolean existsByUser_IdAndProductId(Long userId, String productId);

    @Transactional
    int deleteByProductId(String id);
}
