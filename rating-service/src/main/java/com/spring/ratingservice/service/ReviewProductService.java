package com.spring.ratingservice.service;

import com.spring.ratingservice.domain.ProductRatingDTO;
import com.spring.ratingservice.domain.ProductRatingDetailDTO;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface ReviewProductService {
    ProductRatingDTO getRatingOverall(String id);

    List<ProductRatingDTO> getRatingOverall(List<String> ids);

    ProductRatingDetailDTO getProductRatingDetail(String id, Pageable pageable);
}
