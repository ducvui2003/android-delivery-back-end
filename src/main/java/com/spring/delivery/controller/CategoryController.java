package com.spring.delivery.controller;

import com.spring.delivery.domain.request.product.RequestCategoryCreated;
import com.spring.delivery.domain.response.product.CategoryDTO;
import com.spring.delivery.service.product.CategoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@RequestMapping("/api/v1/category")
public class CategoryController {
    CategoryService categoryService;

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getById(@PathVariable("id") String id) {
        return ResponseEntity.ok(categoryService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAll() {
        return ResponseEntity.ok(categoryService.findAll());
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> create(@RequestBody RequestCategoryCreated request) {
        return ResponseEntity.ok(categoryService.save(request));
    }
}
