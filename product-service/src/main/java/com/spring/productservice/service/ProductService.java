package com.spring.productservice.service;

import com.spring.productservice.domain.ApiPaging;
import com.spring.productservice.domain.response.ResponseProduct;
import com.spring.productservice.domain.response.ResponseProductDetail;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ProductService {
    ApiPaging<ResponseProduct> findAll(Pageable pageable);

    ResponseProductDetail findById(Long id);
}
