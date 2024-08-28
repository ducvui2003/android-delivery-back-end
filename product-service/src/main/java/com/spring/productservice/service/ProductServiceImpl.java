package com.spring.productservice.service;

import com.spring.productservice.domain.ApiPaging;
import com.spring.productservice.domain.response.ResponseProduct;
import com.spring.productservice.domain.response.ResponseProductDetail;
import com.spring.productservice.domain.response.ResponseProductRating;
import com.spring.productservice.mapper.ProductMapper;
import com.spring.productservice.model.Product;
import com.spring.productservice.repository.ProductRepository;
import com.spring.productservice.service.http.HttpRatingService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class ProductServiceImpl implements ProductService {
    ProductRepository productRepository;
    ProductMapper productMapper = ProductMapper.INSTANCE;
    HttpRatingService httpRatingService;

    // TODO: Get Image from service image
    @Override
    public ApiPaging<ResponseProduct> findAll(Pageable pageable) {
        Page<Product> page = productRepository.findAll(pageable);
        if (page.isEmpty())
            return null;

        List<ResponseProduct> responseProducts = page.getContent().stream()
                .map(productMapper::toResponseProduct)
                .toList();
        setRating(responseProducts);

        return ApiPaging.<ResponseProduct>builder()
                .data(responseProducts)
                .current(page.getNumber())
                .size(page.getSize())
                .totalPage(page.getTotalPages())
                .build();
    }

    // TODO: Get Image from service image
    @Override
    public ResponseProductDetail findById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) return null;
        ResponseProductDetail responseProductDetail = productMapper.toResponseProductDetail(product.get());
        setRating(responseProductDetail);
        return responseProductDetail;
    }

    // Exception
    private void setRating(List<ResponseProduct> responseProducts) {
        List<Long> productIds = responseProducts.stream().map(ResponseProduct::getId).toList();
        Map<Long, ResponseProduct> responseProductMap = responseProducts.stream()
                .collect(Collectors.toMap(ResponseProduct::getId, product -> product));

        // Call api to rating service
        List<ResponseProductRating> productRatings = httpRatingService.getRating(productIds).data();
        productRatings.forEach(productRating ->
                Optional.ofNullable(responseProductMap.get(productRating.productId()))
                        .ifPresent(responseProduct -> responseProduct.setRating(productRating.averageRating())));
    }

    private void setRating(ResponseProductDetail responseProductDetail) {
        ResponseProductRating productRating = httpRatingService.getRating(responseProductDetail.getId()).data();

        // Call api to rating service
        responseProductDetail.setRating(productRating.averageRating());
    }
}
