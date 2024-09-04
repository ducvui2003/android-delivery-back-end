package com.spring.ratingservice.service;

import com.spring.ratingservice.domain.ApiPaging;
import com.spring.ratingservice.domain.ProductRatingDTO;
import com.spring.ratingservice.domain.ProductRatingDetailDTO;
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
    public ProductRatingDTO getRatingOverall(String id) {
        Map<Rating, Long> rating = reviewProductRepository.findRatingByProductId(id).orElse(null);
        long totalReview = rating == null ? 0 : rating.values().stream().mapToLong(Long::longValue).sum();
        double averageRating = rating == null ? 0 : rating.entrySet().stream().mapToDouble(e -> e.getKey().getValue() * e.getValue()).sum() / totalReview;
        Map<Integer, Long> ratingDistribution = rating == null ? null : rating.entrySet().stream()
                .collect(Collectors.toMap(e -> e.getKey().getValue(), Map.Entry::getValue));
        return ProductRatingDTO.builder()
                .productId(id)
                .totalReview(totalReview)
                .averageRating(averageRating)
                .ratingDistribution(ratingDistribution)
                .build();
    }

    @Override
    public List<ProductRatingDTO> getRatingOverall(List<String> ids) {
        List<ProductRatingDTO> result = new ArrayList<>();
        Map<String, EnumMap<Rating, Long>> ratingMap = reviewProductRepository.findRatingByProductIds(new HashSet<>(ids));
        for (String id : ids) {
            Map<Rating, Long> rating = ratingMap.get(id);
            Map<Integer, Long> ratingDistribution;
            long totalReview = 0;
            double averageRating = 0;
            if (rating == null)
                ratingDistribution = Map.of();
            else {
                ratingDistribution = rating.entrySet().stream()
                        .collect(Collectors.toMap(e -> e.getKey().getValue(), Map.Entry::getValue));
                totalReview = rating.values().stream().mapToLong(Long::longValue).sum();
                averageRating = rating.entrySet().stream().mapToDouble(e -> e.getKey().getValue() * e.getValue()).sum() / totalReview;
            }
            result.add(ProductRatingDTO.builder().productId(id).totalReview(totalReview).averageRating(averageRating).ratingDistribution(ratingDistribution).build());
        }
        return result;
    }

    @Override
    public ApiPaging<ProductRatingDetailDTO> getProductRatingDetail(String id, Pageable pageable) {
        Page<ReviewProduct> reviewProducts = reviewProductRepository.findByProductId(id, pageable);
        List<ProductRatingDetailDTO> data = reviewProducts.isEmpty() ? Collections.emptyList() : reviewProducts.stream()
                .map(reviewMapper::toProductRatingDetailDTO)
                .toList();
        return ApiPaging.<ProductRatingDetailDTO>builder()
                .current(pageable.getPageNumber())
                .totalPage(reviewProducts.getTotalPages())
                .size(pageable.getPageSize())
                .content(data)
                .build();
    }

    @Override
    public ApiPaging<ProductRatingDetailDTO> getProductRatingDetail(String id, Rating rating, Pageable pageable) {
        Specification<ReviewProduct> specs = ReviewProductSpecs.hasRating(rating).and(ReviewProductSpecs.hasProductId(id));
        Page<ReviewProduct> reviewProducts = reviewProductRepository.findAll(specs, pageable);
        if (reviewProducts.isEmpty())
            return null;
        List<ProductRatingDetailDTO> data = reviewProducts.stream()
                .map(reviewMapper::toProductRatingDetailDTO)
                .toList();
        return ApiPaging.<ProductRatingDetailDTO>builder()
                .current(pageable.getPageNumber())
                .totalPage(reviewProducts.getTotalPages())
                .size(pageable.getPageSize())
                .content(data)
                .build();
    }
}
