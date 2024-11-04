package com.spring.delivery.controller;

import com.spring.delivery.domain.request.product.RequestOptionCreated;
import com.spring.delivery.domain.response.product.ProductOptionDTO;
import com.spring.delivery.service.product.IProductOptionService;
import com.spring.delivery.util.anotation.ApiMessage;
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
    IProductOptionService productOptionService;


    @GetMapping("/{id}")
    @ApiMessage("Get product option by id")
    public ResponseEntity<ProductOptionDTO> getById(@PathVariable("id") String id) {
        return ResponseEntity.ok().body(productOptionService.findById(id));
    }

    @GetMapping
    @ApiMessage("Get all product option")
    public ResponseEntity<List<ProductOptionDTO>> getAll() {
        return ResponseEntity.ok().body(productOptionService.findAll());
    }

    @PostMapping
    @ApiMessage("Create product option")
    public ResponseEntity<ProductOptionDTO> create(@RequestBody RequestOptionCreated request) {
        return ResponseEntity.ok().body(productOptionService.save(request));
    }
}
