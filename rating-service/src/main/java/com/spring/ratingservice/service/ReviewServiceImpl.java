package com.spring.ratingservice.service;

import com.spring.ratingservice.domain.ProductRatingDTO;
import com.spring.ratingservice.repository.ReviewRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j

public class ReviewServiceImpl implements ReviewService {
    ReviewRepository reviewRepository;

    @Override
    public ProductRatingDTO getProductRating(Long id) {
        return reviewRepository.findRatingByProductId(id).orElse(ProductRatingDTO.builder().productId(id).build());
    }

    @Override
    public List<ProductRatingDTO> getProductRating(List<Long> ids) {
        return reviewRepository.findRatingByProductIds(new HashSet<>(ids));
    }
}
