package com.spring.ratingservice.service;

import com.spring.ratingservice.domain.ApiPaging;
import com.spring.ratingservice.domain.ProductReviewDTO;
import com.spring.ratingservice.domain.ProductReviewDetailDTO;
import com.spring.ratingservice.util.constraint.Rating;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReviewProductService {
    ProductReviewDTO getRatingOverall(String id);

    List<ProductReviewDTO> getRatingOverall(List<String> ids);

    ApiPaging<ProductReviewDetailDTO> getProductRatingDetail(String id, Pageable pageable);

    ApiPaging<ProductReviewDetailDTO> getProductRatingDetail(String id, Rating rating, Pageable pageable);

}
