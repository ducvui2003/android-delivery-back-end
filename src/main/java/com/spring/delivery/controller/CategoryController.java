package com.spring.delivery.controller;

import com.spring.delivery.domain.request.product.RequestCategoryCreated;
import com.spring.delivery.domain.response.product.CategoryDTO;
import com.spring.delivery.service.business.product.ICategoryService;
import com.spring.delivery.util.anotation.ApiMessage;
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
    ICategoryService categoryService;

    @GetMapping("/{id}")
    @ApiMessage("Get category by id")
    public ResponseEntity<CategoryDTO> getById(@PathVariable("id") String id) {
        return ResponseEntity.ok().body(categoryService.findById(id));
    }

    @GetMapping
    @ApiMessage("Get all category")
    public ResponseEntity<List<CategoryDTO>> getAll() {
        return ResponseEntity.ok().body(categoryService.findAll());
    }

    @PostMapping
    @ApiMessage("Create category")
    public ResponseEntity<CategoryDTO> create(@RequestBody RequestCategoryCreated request) {
        return ResponseEntity.ok().body(categoryService.save(request));
    }
}
