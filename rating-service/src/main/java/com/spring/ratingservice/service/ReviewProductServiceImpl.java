package com.spring.ratingservice.service;

import com.spring.ratingservice.domain.ApiPaging;
import com.spring.ratingservice.domain.ProductReviewDTO;
import com.spring.ratingservice.domain.ProductReviewDetailDTO;
import com.spring.ratingservice.mapper.ReviewMapper;
import com.spring.ratingservice.model.ReviewProduct;
import com.spring.ratingservice.repository.ReviewProductProductRepository;
import com.spring.ratingservice.service.specification.ReviewProductSpecs;
import com.spring.ratingservice.util.constraint.Rating;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j

public class ReviewProductServiceImpl implements ReviewProductService {
    ReviewProductProductRepository reviewProductRepository;
    ReviewMapper reviewMapper = ReviewMapper.INSTANCE;

    @Override
    public ProductReviewDTO getRatingOverall(String id) {
        return reviewProductRepository.findProductReviewDTOByProductId(id)
                .orElse(ProductReviewDTO.builder().productId(id).totalReview(0).averageRating(0).ratingDistribution(null).build());
    }

    @Override
    public List<ProductReviewDTO> getRatingOverall(List<String> ids) {
        return reviewProductRepository.findProductReviewDTOByProductIds(new HashSet<>(ids));
    }

    @Override
    public ApiPaging<ProductReviewDetailDTO> getProductRatingDetail(String id, Pageable pageable) {
        Page<ReviewProduct> reviewProducts = reviewProductRepository.findByProductId(id, pageable);
        List<ProductReviewDetailDTO> data = reviewProducts.isEmpty() ? Collections.emptyList() : reviewProducts.stream()
                .map(reviewMapper::toProductRatingDetailDTO)
                .toList();
        return ApiPaging.<ProductReviewDetailDTO>builder()
                .current(pageable.getPageNumber())
                .totalPage(reviewProducts.getTotalPages())
                .size(pageable.getPageSize())
                .content(data)
                .build();
    }

    @Override
    public ApiPaging<ProductReviewDetailDTO> getProductRatingDetail(String id, Rating rating, Pageable pageable) {
        Specification<ReviewProduct> specs = ReviewProductSpecs.hasRating(rating).and(ReviewProductSpecs.hasProductId(id));
        Page<ReviewProduct> reviewProducts = reviewProductRepository.findAll(specs, pageable);
        if (reviewProducts.isEmpty())
            return null;
        List<ProductReviewDetailDTO> data = reviewProducts.stream()
                .map(reviewMapper::toProductRatingDetailDTO)
                .toList();
        return ApiPaging.<ProductReviewDetailDTO>builder()
                .current(pageable.getPageNumber() + 1)
                .totalPage(reviewProducts.getTotalPages())
                .size(pageable.getPageSize())
                .content(data)
                .build();
    }
}
