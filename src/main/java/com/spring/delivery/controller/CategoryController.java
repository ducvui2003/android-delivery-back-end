package com.spring.delivery.controller;

import com.spring.delivery.domain.request.product.RequestCategoryCreated;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
    @RequestMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") String id) {
        return null;
    }

    @RequestMapping
    public ResponseEntity<?> getAll() {
        return null;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody RequestCategoryCreated request){
        return null;
    }
}
