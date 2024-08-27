package com.spring.productservice.service.http;

import com.spring.productservice.domain.ApiResponse;
import com.spring.productservice.domain.response.ResponseProductRating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "rating-service", url = "${app.service.rating}")
public interface HttpRatingService {
    @PostMapping("/api/v1/rating")
    public ApiResponse<ResponseProductRating> getRating(@RequestBody List<Long> productId);
}
