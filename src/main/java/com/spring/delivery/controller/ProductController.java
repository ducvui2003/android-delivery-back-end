package com.spring.delivery.controller;

import com.spring.delivery.domain.request.product.RequestDiscountCreated;
import com.spring.delivery.domain.request.product.RequestProductCreated;
import com.spring.delivery.domain.request.product.RequestUpdateImage;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int id) {
        return null;
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return null;
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<?> getByCategory(@PathVariable("id") String id) {
        return null;
    }

    @GetMapping
    public ResponseEntity<?> createProduct(@Valid @RequestBody RequestProductCreated request) {
        return null;
    }

    @DeleteMapping("/discount/{id}")
    public ResponseEntity<?> removeDiscount(@PathVariable("id") String id) {
        return null;
    }

    @PutMapping("/discount/{id}")
    public ResponseEntity<?> setDiscount(@PathVariable("id") String id, @Valid @RequestBody RequestDiscountCreated request) {
        return null;
    }

    @PutMapping("/image/{id}")
    public ResponseEntity<?> updateUrlImage(@PathVariable("id") String id, @Valid @RequestBody RequestUpdateImage request) {
        return null;
    }
}
