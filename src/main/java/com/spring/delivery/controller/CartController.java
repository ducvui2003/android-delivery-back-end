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

@RestController
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
public class CartController {
    ICartServices cartServices;
    SecurityUtil securityUtil;

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @ApiMessage("Add Cart Item")
    @PostMapping
    public ResponseEntity<ResponseCart> addCartItem(@RequestBody RequestCartCreated request) {
        Long userId = securityUtil.getCurrentUserDTOFromAccessToken().orElse(null).id();
        ResponseCart response = cartServices.addCartItem(userId, request);
        if (response != null) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.badRequest().build();
    }


    @GetMapping
    public ResponseEntity<?> getAllCartItem() {
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCartItem(@PathVariable("id") Integer id) {
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCartItem(@PathVariable("id") Integer id, @RequestBody RequestCartUpdated request) {
        return null;
    }

    @DeleteMapping
    public ResponseEntity<?> deleteCartItem() {
        return null;
    }

}
