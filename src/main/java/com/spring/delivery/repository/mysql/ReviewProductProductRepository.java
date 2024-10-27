package com.spring.delivery.repository.mysql;

import com.spring.delivery.domain.response.review.ProductReviewResponse;
import com.spring.delivery.model.ReviewProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface ReviewProductProductRepository extends JpaRepository<ReviewProduct, Long>, JpaSpecificationExecutor<ReviewProduct> {
    Optional<ReviewProduct> findById(Long id);

    Page<ReviewProduct> findByProductId(String id, Pageable pageable);

    Page<ReviewProduct> findAll(Specification<ReviewProduct> specification ,Pageable pageable);


    @Query("""
                   SELECT
                        new com.spring.delivery.domain.response.review.ProductReviewResponse(
                              tb1.productId,
                              SUM(tb1.count),
                              (CAST(SUM(CAST(tb1.rating AS LONG ) * tb1.count) AS DOUBLE) / SUM(tb1.count)),
                               CAST(json_objectagg(tb1.rating, tb1.count) AS string )
                        )
                   FROM (
                        SELECT review.productId AS productId, review.rating AS rating, COUNT(review.rating) AS count
                        FROM ReviewProduct review
                        WHERE review.productId = :productId
                        GROUP BY review.rating, review.productId
                   ) AS tb1
                   GROUP BY tb1.productId
            """)
    Optional<ProductReviewResponse> findProductReviewDTOByProductId(@Param("productId") String productId);

    @Query("""
                   SELECT
                        new com.spring.delivery.domain.response.review.ProductReviewResponse(
                              tb1.productId,
                              SUM(tb1.count),
                              (CAST(SUM(CAST(tb1.rating AS LONG ) * tb1.count) AS DOUBLE) / SUM(tb1.count))
                        )
                   FROM (
                        SELECT review.productId AS productId, review.rating AS rating, COUNT(review.rating) AS count
                        FROM ReviewProduct review
                        WHERE review.productId IN :productIds
                        GROUP BY review.rating, review.productId
                   ) AS tb1
                   GROUP BY tb1.productId
            """)
    List<ProductReviewResponse> findProductReviewDTOByProductIds(@Param("productIds") Set<String> productIds);
}
