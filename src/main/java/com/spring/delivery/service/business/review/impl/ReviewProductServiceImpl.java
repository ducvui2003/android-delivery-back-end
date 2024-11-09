package com.spring.delivery.service.business.review.impl;

import com.spring.delivery.domain.ApiPaging;
import com.spring.delivery.domain.response.review.AverageRatingProduct;
import com.spring.delivery.domain.response.review.ProductReviewResponse;
import com.spring.delivery.domain.response.review.ProductReviewDetailResponse;
import com.spring.delivery.mapper.ReviewMapper;
import com.spring.delivery.model.ReviewProduct;
import com.spring.delivery.repository.mysql.IReviewProductRepository;
import com.spring.delivery.service.business.review.IReviewProductService;
import com.spring.delivery.service.business.review.spec.ReviewProductSpecs;
import com.spring.delivery.util.enums.Rating;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class ReviewProductServiceImpl implements IReviewProductService {
    final IReviewProductRepository reviewProductRepository;
    final ReviewMapper reviewMapper;
    @Value("${app.database.entry.product-review.limit}")
    int limit;

    @Override
    public ProductReviewResponse getRatingOverall(String id) {
        return reviewProductRepository.findProductReviewDTOByProductId(id)
                .orElse(ProductReviewResponse.builder().productId(id).totalReview(0).averageRating(0).ratingDistribution(null).build());
    }

    @Override
    public List<ProductReviewResponse> getRatingOverall(List<String> ids) {
        return reviewProductRepository.findProductReviewDTOByProductIds(new HashSet<>(ids));
    }

    @Override
    public ApiPaging<ProductReviewDetailResponse> getProductRatingDetail(String id, Pageable pageable) {
        Page<ReviewProduct> reviewProducts = reviewProductRepository.findByProductId(id, pageable);
        List<ProductReviewDetailResponse> data = reviewProducts.isEmpty() ? Collections.emptyList() : reviewProducts.stream()
                .map(reviewMapper::toProductRatingDetailDTO)
                .toList();
        return ApiPaging.<ProductReviewDetailResponse>builder()
                .current(pageable.getPageNumber())
                .totalPage(reviewProducts.getTotalPages())
                .size(pageable.getPageSize())
                .content(data)
                .build();
    }

    @Override
    public ApiPaging<ProductReviewDetailResponse> getProductRatingDetail(String id, Rating rating, Pageable pageable) {
        Specification<ReviewProduct> specs = ReviewProductSpecs.hasRating(rating).and(ReviewProductSpecs.hasProductId(id));
        Page<ReviewProduct> reviewProducts = reviewProductRepository.findAll(specs, pageable);
        if (reviewProducts.isEmpty())
            return null;
        List<ProductReviewDetailResponse> data = reviewProducts.stream()
                .map(reviewMapper::toProductRatingDetailDTO)
                .toList();
        return ApiPaging.<ProductReviewDetailResponse>builder()
                .current(pageable.getPageNumber() + 1)
                .totalPage(reviewProducts.getTotalPages())
                .size(pageable.getPageSize())
                .content(data)
                .build();
    }

    @Override
    public List<AverageRatingProduct> findAverageRatingProduct() {
        return reviewProductRepository.findAverageRatingProduct(limit);
    }
}
