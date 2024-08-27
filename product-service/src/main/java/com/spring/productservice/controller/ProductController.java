package com.spring.productservice.controller;

import com.spring.productservice.domain.ApiPaging;
import com.spring.productservice.domain.response.ResponseProduct;
import com.spring.productservice.domain.response.ResponseProductDetail;
import com.spring.productservice.service.ProductService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    ProductService productService;

    @GetMapping("/")
    public ResponseEntity<ApiPaging<ResponseProduct>> getProductList(@PageableDefault(page = 0, value = 10) Pageable pageable) {
        if (pageable == null)
            return ResponseEntity.ok(null);

        return ResponseEntity.ok(productService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseProductDetail> getProductDetail(@PathVariable("id") Long id) {
        Optional<ResponseProductDetail> optionalResponseProductDetail = productService.findById(id);
        if (optionalResponseProductDetail.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(optionalResponseProductDetail.get());
    }
}
