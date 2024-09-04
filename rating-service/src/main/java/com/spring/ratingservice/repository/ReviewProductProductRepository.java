package com.spring.ratingservice.repository;

import com.spring.ratingservice.model.ReviewProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReviewProductProductRepository extends JpaRepository<ReviewProduct, Long>, JpaSpecificationExecutor<ReviewProduct>, CustomReviewProductRepository {
    Optional<ReviewProduct> findById(Long id);

    Page<ReviewProduct> findByProductId(String id, Pageable pageable);

    Page<ReviewProduct> findAll(Specification<ReviewProduct> specification ,Pageable pageable);

}
