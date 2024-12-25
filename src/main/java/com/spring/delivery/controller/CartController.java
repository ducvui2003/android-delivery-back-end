package com.spring.delivery.controller;

import com.spring.delivery.domain.request.cart.RequestCartCreated;
import com.spring.delivery.domain.request.cart.RequestCartUpdated;
import com.spring.delivery.domain.response.cart.ResponseCart;
import com.spring.delivery.service.business.cart.ICartServices;
import com.spring.delivery.util.SecurityUtil;
import com.spring.delivery.util.anotation.ApiMessage;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
public class CartController {
    ICartServices cartServices;
    SecurityUtil securityUtil;
    int INCREASE_QUANTITY = 1;
    int DECREASE_QUANTITY = 1;

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @ApiMessage("Add Cart Item")
    @PostMapping("/add")
    public ResponseEntity<ResponseCart> addCartItem(@RequestBody RequestCartCreated request) {
        Long userId = securityUtil.getCurrentUserDTOFromAccessToken().orElse(null).id();
        ResponseCart response = cartServices.addCartItem(userId, request);
        if (response != null) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/list")
    public ResponseEntity<List<ResponseCart>> getAllCartItem() {
        Long userId = securityUtil.getCurrentUserDTOFromAccessToken().orElse(null).id();
        List<ResponseCart> response = cartServices.getAllCartItem(userId);
        if (response == null || response.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<ResponseCart> getCartItem(@PathVariable("id") Long id) {
        Long userId = securityUtil.getCurrentUserDTOFromAccessToken().orElse(null).id();
        ResponseCart response = cartServices.getCartItem(userId, id);
        if (response != null) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/increase/{id}")
    public ResponseEntity<Void> increaseCartItem(@PathVariable("id") Long id) {
        Long userId = securityUtil.getCurrentUserDTOFromAccessToken().orElse(null).id();
        boolean isSuccess = cartServices.increaseQuantity(userId, id, INCREASE_QUANTITY);
        if (isSuccess) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/decrease/{id}")
    public ResponseEntity<Void> decreaseCartItem(@PathVariable("id") Long id) {
        Long userId = securityUtil.getCurrentUserDTOFromAccessToken().orElse(null).id();
        boolean isSuccess = cartServices.decreaseQuantity(userId, id, DECREASE_QUANTITY);
        if (isSuccess) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCartItem(@PathVariable("id") Long id) {
        Long userId = securityUtil.getCurrentUserDTOFromAccessToken().orElse(null).id();
        boolean isDeleted = cartServices.deleteCartItem(userId, id);
        if (isDeleted) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
