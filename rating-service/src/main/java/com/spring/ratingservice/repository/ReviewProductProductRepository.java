package com.spring.ratingservice.repository;

import com.spring.ratingservice.model.ReviewOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReviewProductProductRepository extends JpaRepository<ReviewOrder, Long>, CustomReviewProductRepository {
    Optional<ReviewOrder> findById(Long id);

}
