package com.spring.productservice.service.http;

import com.spring.productservice.domain.ApiResponse;
import com.spring.productservice.domain.response.ResponseProductRating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "rating-service", url = "${app.service.rating}/api/v1/rating")
public interface HttpRatingService {
    @GetMapping("/list")
    public ApiResponse<List<ResponseProductRating>> getRating(@RequestParam("productIds") List<Long> productIds);

    @GetMapping("/{productId}")
    public ApiResponse<ResponseProductRating> getRating(@PathVariable("productId") Long productId);
}
