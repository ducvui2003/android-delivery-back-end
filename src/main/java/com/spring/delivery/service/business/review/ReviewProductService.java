package com.spring.delivery.service.business.review;

import com.spring.delivery.domain.ApiPaging;
import com.spring.delivery.domain.response.review.ProductReviewResponse;
import com.spring.delivery.domain.response.review.ProductReviewDetailResponse;
import com.spring.delivery.util.enums.Rating;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReviewProductService {
    ProductReviewResponse getRatingOverall(String id);

    List<ProductReviewResponse> getRatingOverall(List<String> ids);

    ApiPaging<ProductReviewDetailResponse> getProductRatingDetail(String id, Pageable pageable);

    ApiPaging<ProductReviewDetailResponse> getProductRatingDetail(String id, Rating rating, Pageable pageable);
}
