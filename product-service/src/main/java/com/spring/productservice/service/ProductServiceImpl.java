package com.spring.productservice.service;

import com.spring.productservice.domain.ApiPaging;
import com.spring.productservice.domain.response.ResponseProduct;
import com.spring.productservice.domain.response.ResponseProductDetail;
import com.spring.productservice.mapper.ProductMapper;
import com.spring.productservice.model.Product;
import com.spring.productservice.repository.ProductRepository;
import com.spring.productservice.service.http.HttpRatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    ProductRepository productRepository;
    ProductMapper productMapper = ProductMapper.INSTANCE;
    HttpRatingService httpRatingService;
    // TODO: Get Image from service image
    // TODO Get rating from service rating
    @Override
    public ApiPaging<ResponseProduct> findAll(Pageable pageable) {
        Page<Product> page = productRepository.findAll(pageable);
        if (page.isEmpty()) {
            return null;
        }
        List<ResponseProduct> responseProducts = page.getContent().stream()
                .map(productMapper::toResponseProduct)

                .toList();


        return ApiPaging.<ResponseProduct>builder()
                .data(responseProducts)
                .current(page.getNumber())
                .size(page.getSize())
                .totalPage(page.getTotalPages())
                .build();
    }

    @Override
    public ResponseProductDetail findById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) return null;
        ResponseProductDetail responseProductDetail = productMapper.toResponseProductDetail(product.get());
        // TODO: Get Image from service image
        

        // TODO Get rating from service rating

        return responseProductDetail;
    }
}
