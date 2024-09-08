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

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j

public class ReviewProductServiceImpl implements ReviewProductService {
    ReviewProductProductRepository reviewProductRepository;
    ReviewMapper reviewMapper = ReviewMapper.INSTANCE;

    @Override
    public ProductReviewDTO getRatingOverall(String id) {
        Map<Rating, Long> rating = reviewProductRepository.findRatingByProductId(id).orElse(null);
        if (rating == null)
            return ProductReviewDTO.builder().productId(id).totalReview(0).averageRating(0).ratingDistribution(null).build();
        long totalReview = rating.values().stream().mapToLong(Long::longValue).sum();
        double averageRating = rating.entrySet().stream().mapToDouble(e -> e.getKey().getValue() * e.getValue()).sum() / totalReview;
        Map<Integer, Long> ratingDistribution = rating.entrySet().stream()
                .collect(Collectors.toMap(e -> e.getKey().getValue(), Map.Entry::getValue));
        return ProductReviewDTO.builder()
                .productId(id)
                .totalReview(totalReview)
                .averageRating(averageRating)
                .ratingDistribution(ratingDistribution)
                .build();
    }

    @Override
    public List<ProductReviewDTO> getRatingOverall(List<String> ids) {
        List<ProductReviewDTO> result = new ArrayList<>();
        Map<String, EnumMap<Rating, Long>> ratingMap = reviewProductRepository.findRatingByProductIds(new HashSet<>(ids));
        if (ratingMap.isEmpty())
            return Collections.emptyList();
        for (String id : ids) {
            Map<Rating, Long> rating = ratingMap.get(id);
            long totalReview = 0;
            double averageRating = 0;
            if (rating != null) {
                totalReview = rating.values().stream().mapToLong(Long::longValue).sum();
                averageRating = rating.entrySet().stream().mapToDouble(e -> e.getKey().getValue() * e.getValue()).sum() / totalReview;
            }
            result.add(ProductReviewDTO.builder().productId(id).totalReview(totalReview).averageRating(averageRating).build());
        }
        return result;
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
