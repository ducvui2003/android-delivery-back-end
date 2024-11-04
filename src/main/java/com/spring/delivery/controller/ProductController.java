package com.spring.delivery.controller;

import com.spring.delivery.domain.ApiPaging;
import com.spring.delivery.domain.request.product.RequestDiscountCreated;
import com.spring.delivery.domain.request.product.RequestProductCreated;
import com.spring.delivery.domain.request.product.RequestProductUpdated;
import com.spring.delivery.domain.request.product.RequestUpdateImage;
import com.spring.delivery.domain.response.product.CardProductDTO;
import com.spring.delivery.domain.response.product.ProductDTO;
import com.spring.delivery.service.product.IProductService;
import com.spring.delivery.util.SecurityUtil;
import com.spring.delivery.util.anotation.ApiMessage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class ProductController {
    IProductService productService;


    @GetMapping("/{id}")
    @ApiMessage("Get product by id")
    public ResponseEntity<ProductDTO> getById(HttpServletRequest request, @PathVariable("id") String id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @GetMapping
    @ApiMessage("Get all product per page")
    public ResponseEntity<ApiPaging<CardProductDTO>> getAll(@RequestParam(required = false, name = "page", defaultValue = "0") int page) {
        return ResponseEntity.ok(productService.findAll(page));
    }

    @GetMapping("/category/{id}")
    @ApiMessage("Get product by id categoryId")
    public ResponseEntity<ApiPaging<CardProductDTO>> getByCategory(@PathVariable("id") String id, @RequestParam(required = false, name = "page", defaultValue = "0") int page) {
        return ResponseEntity.ok(productService.findAllByCategoryId(id, page));
    }

    @PostMapping(consumes = "application/json;charset=UTF-8")
    @ApiMessage("Create product")
    public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody RequestProductCreated request) {
        return ResponseEntity.ok(productService.save(request));
    }

    @PutMapping(path = "/{id}", consumes = "application/json;charset=UTF-8")
    @ApiMessage("Create product")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable("id") String id, @Valid @RequestBody RequestProductUpdated request) {
        return ResponseEntity.ok(productService.updateProduct(id, request));
    }

    @DeleteMapping("/discount/{id}")
    @ApiMessage("Remove discount product")
    public ResponseEntity<ProductDTO> removeDiscount(@PathVariable("id") String id) {
        return ResponseEntity.ok(productService.removeDiscount(id));
    }

    @PutMapping("/discount/{id}")
    @ApiMessage("Set discount for product")
    public ResponseEntity<ProductDTO> setDiscount(@PathVariable("id") String id, @Valid @RequestBody RequestDiscountCreated request) {
        return ResponseEntity.ok(productService.setDiscount(id, request));
    }

    @PutMapping("/image/{id}")
    @ApiMessage("Update url image product")
    public ResponseEntity<ProductDTO> updateUrlImage(@PathVariable("id") String id, @Valid @RequestBody RequestUpdateImage request) {
        return ResponseEntity.ok(productService.updateUrlImage(id, request));
    }

    @DeleteMapping("/{id}")
    @ApiMessage("Delete product by id")
    public ResponseEntity<ProductDTO> deleteProduct(@PathVariable("id") String id) {
        return ResponseEntity.ok(productService.deleteProduct(id));
    }

    @PostMapping("/{id}")
    @ApiMessage("Undelete product by id")
    public ResponseEntity<ProductDTO> unDeleteProduct(@PathVariable("id") String id) {
        return ResponseEntity.ok(productService.unDeleteProduct(id));
    }

    @GetMapping("test")
    @ApiMessage("Find product for home page")
    public ResponseEntity<List<ProductDTO>> findProductForHomePage() {
        return ResponseEntity.ok(productService.findProductForHomePage());
    }
}
