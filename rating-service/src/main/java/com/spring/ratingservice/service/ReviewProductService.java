package com.spring.ratingservice.service;

import com.spring.ratingservice.domain.ApiPaging;
import com.spring.ratingservice.domain.ProductRatingDTO;
import com.spring.ratingservice.domain.ProductRatingDetailDTO;
import com.spring.ratingservice.util.constraint.Rating;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReviewProductService {
    ProductRatingDTO getRatingOverall(String id);

    List<ProductRatingDTO> getRatingOverall(List<String> ids);

    ApiPaging<ProductRatingDetailDTO> getProductRatingDetail(String id, Pageable pageable);

    ApiPaging<ProductRatingDetailDTO> getProductRatingDetail(String id, Rating rating, Pageable pageable);

}
