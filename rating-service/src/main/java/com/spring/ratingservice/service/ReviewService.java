package com.spring.ratingservice.service;

import com.spring.ratingservice.domain.ProductRatingDTO;

import java.util.List;

public interface ReviewService {
    ProductRatingDTO getProductRating(Long id);

    List<ProductRatingDTO> getProductRating(List<Long> ids);
}
