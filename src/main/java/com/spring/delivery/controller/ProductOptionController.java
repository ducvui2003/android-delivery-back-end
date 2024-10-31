package com.spring.delivery.controller;

import com.spring.delivery.domain.request.product.RequestOptionGroupCreated;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product-option")
public class ProductOptionController {
    @RequestMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") String id) {
        return null;
    }

    @RequestMapping
    public ResponseEntity<?> getAll() {
        return null;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody RequestOptionGroupCreated request) {
        return null;
    }
}
