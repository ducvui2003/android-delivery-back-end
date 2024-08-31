package com.spring.ratingservice.service;

import com.spring.ratingservice.domain.ProductRatingDTO;
import com.spring.ratingservice.domain.ProductRatingDetailDTO;
import com.spring.ratingservice.repository.ReviewProductProductRepository;
import com.spring.ratingservice.util.constraint.Rating;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j

public class ReviewProductServiceImpl implements ReviewProductService {
    ReviewProductProductRepository reviewProductRepository;

    @Override
    public ProductRatingDTO getRatingOverall(String id) {
        return reviewProductRepository.findRatingByProductId(id).orElse(ProductRatingDTO.builder().productId(id).build());
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
    public ProductRatingDetailDTO getProductRatingDetail(String id, Pageable pageable) {
        return null;
    }
}
