package com.spring.productservice.controller;

import com.spring.productservice.domain.ApiPaging;
import com.spring.productservice.domain.response.ResponseProduct;
import com.spring.productservice.domain.response.ResponseProductDetail;
import com.spring.productservice.service.ProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class ProductController {
    ProductService productService;

    @GetMapping("/")
    public ResponseEntity<ApiPaging<ResponseProduct>> getProductList(@PageableDefault(page = 0, value = 10) Pageable pageable) {
        return ResponseEntity.ok(productService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseProductDetail> getProductDetail(@PathVariable("id") Long id) {
        ResponseProductDetail productDetail = productService.findById(id);
        if (productDetail == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(productDetail);
    }
}
