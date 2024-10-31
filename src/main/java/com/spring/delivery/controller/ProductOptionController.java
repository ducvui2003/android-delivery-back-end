package com.spring.delivery.controller;

import com.spring.delivery.domain.request.product.RequestOptionCreated;
import com.spring.delivery.domain.response.product.ProductOptionDTO;
import com.spring.delivery.service.product.ProductOptionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/product-option")
public class ProductOptionController {
    ProductOptionService productOptionService;


    @GetMapping("/{id}")
    public ResponseEntity<ProductOptionDTO> getById(@PathVariable("id") String id) {
        return ResponseEntity.ok(productOptionService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<ProductOptionDTO>> getAll() {
        return ResponseEntity.ok(productOptionService.findAll());
    }

    @PostMapping
    public ResponseEntity<ProductOptionDTO> create(@RequestBody RequestOptionCreated request) {
        return ResponseEntity.ok(productOptionService.save(request));
    }
}
