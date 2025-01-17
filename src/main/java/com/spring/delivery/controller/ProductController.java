package com.spring.delivery.controller;

import com.spring.delivery.domain.ApiPaging;
import com.spring.delivery.domain.request.product.*;
import com.spring.delivery.domain.response.product.CardProductDTO;
import com.spring.delivery.domain.response.product.ProductDTO;
import com.spring.delivery.domain.response.product.ProductOptionDTO;
import com.spring.delivery.service.business.product.IProductOptionService;
import com.spring.delivery.service.business.product.IProductService;
import com.spring.delivery.service.business.user.IUserProductFavoriteService;
import com.spring.delivery.util.anotation.ApiMessage;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class ProductController {
    IProductService productService;
    IUserProductFavoriteService userProductFavoriteService;

    @GetMapping("/{id}")
    @ApiMessage("Get product by id")
    public ResponseEntity<ProductDTO> getById(@PathVariable("id") String id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @GetMapping
    @ApiMessage("Get all product per page")
    public ResponseEntity<ApiPaging<CardProductDTO>> getAll(@RequestParam(required = false, name = "page", defaultValue = "1") int page) {
        return ResponseEntity.ok(productService.findAll(page));
    }

    @GetMapping("/category/{id}")
    @ApiMessage("Get product by id category")
    public ResponseEntity<ApiPaging<CardProductDTO>> getByCategory(@PathVariable("id") String id, @RequestParam(required = false, name = "page", defaultValue = "1") int page) {
        return ResponseEntity.ok(productService.findAllByCategoryId(id, page));
    }

    @PostMapping(consumes = "application/json;charset=UTF-8")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiMessage("Create product")
    public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody RequestProductCreated request) {
        return ResponseEntity.ok(productService.save(request));
    }

    @PutMapping(path = "/{id}", consumes = "application/json;charset=UTF-8")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiMessage("Create product")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable("id") String id, @Valid @RequestBody RequestProductUpdated request) {
        return ResponseEntity.ok(productService.updateProduct(id, request));
    }

    @DeleteMapping("/discount/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiMessage("Remove discount product")
    public ResponseEntity<ProductDTO> removeDiscount(@PathVariable("id") String id) {
        return ResponseEntity.ok(productService.removeDiscount(id));
    }

    @PutMapping("/discount/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiMessage("Set discount for product")
    public ResponseEntity<ProductDTO> setDiscount(@PathVariable("id") String id, @Valid @RequestBody RequestDiscountCreated request) {
        return ResponseEntity.ok(productService.setDiscount(id, request));
    }

    @PutMapping("/image/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiMessage("Update url image product")
    public ResponseEntity<ProductDTO> updateUrlImage(@PathVariable("id") String id, @Valid @RequestBody RequestUpdateImage request) {
        return ResponseEntity.ok(productService.updateUrlImage(id, request));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') AND hasAuthority('PRODUCT_DELETE')")
    @ApiMessage("Delete product by id")
    public ResponseEntity<ProductDTO> deleteProduct(@PathVariable("id") String id) {
        return ResponseEntity.ok(productService.deleteProduct(id));
    }

    @PostMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') AND hasAuthority('PRODUCT_DELETE')")
    @ApiMessage("Undelete product by id")
    public ResponseEntity<ProductDTO> unDeleteProduct(@PathVariable("id") String id) {
        return ResponseEntity.ok(productService.unDeleteProduct(id));
    }

    @GetMapping("/home-page")
    @ApiMessage("Find product for home page")
    public ResponseEntity<List<ProductDTO>> findProductForHomePage() {
        return ResponseEntity.ok(productService.findProductForHomePage());
    }

    @GetMapping("/search")
    @ApiMessage("Search product")
    public ResponseEntity<ApiPaging<CardProductDTO>> searchProduct(RequestSearchProduct request) {
        return ResponseEntity.ok(productService.searchProduct(request));
    }

    @PostMapping("/favorite/{id}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @ApiMessage("Favorite product success")
    public ResponseEntity<Void> favorite(@PathVariable("id") String id) {
        userProductFavoriteService.addProductFavorite(id);
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/favorite/{id}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @ApiMessage("Remove favorite product success")
    public ResponseEntity<Void> unFavorite(@PathVariable("id") String id) {
        userProductFavoriteService.removeProductFavorite(id);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/favorite")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @ApiMessage("Favorite product")
    public ResponseEntity<ApiPaging<CardProductDTO>> getFavorite(RequestSearchProduct request, @PageableDefault(sort = "id") Pageable pageable) {
        var result = productService.getFavorite(request, pageable);
        return ResponseEntity.ok().body(result);
    }
}
