package com.spring.delivery.controller;

import com.spring.delivery.domain.request.order.RequestOrderCreated;
import com.spring.delivery.domain.request.order.RequestUpdateOrder;
import com.spring.delivery.domain.response.order.ResponseOrder;
import com.spring.delivery.domain.response.order.ResponseOrderDetail;
import com.spring.delivery.service.order.OrderService;
import com.spring.delivery.util.SecurityUtil;
import com.spring.delivery.util.anotation.ApiMessage;
import com.spring.delivery.util.enums.StatusOrder;
import com.spring.delivery.util.exception.AppErrorCode;
import com.spring.delivery.util.exception.AppException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class OrderController {
    OrderService orderService;
    SecurityUtil securityUtil;

    @PreAuthorize("hasRole('ADMIN')")
    @ApiMessage("add order")
    @PostMapping("/create")
    public ResponseEntity<ResponseOrder> addOrder(@RequestBody RequestOrderCreated req) {
        Long id = securityUtil.getCurrentUserDTOFromAccessToken().orElseThrow(() -> new AppException(AppErrorCode.USER_NOT_FOUND)).id();
        return ResponseEntity.ok(orderService.createOrder(id, req));
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @ApiMessage("get order by id")
    @GetMapping("/detail/{id}")
    public ResponseEntity<ResponseOrderDetail> getOrderById(@PathVariable("id") Long id) {
        ResponseOrderDetail response = orderService.getOrder(id);
        if (response == null) {
            throw new AppException(AppErrorCode.ORDER_NOT_FOUND);
        }
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ApiMessage("update status")
    @PutMapping("/update/{id}")
    public ResponseEntity<Integer> updateOrderStatus(
            @PathVariable("id") Long id,
            @RequestBody RequestUpdateOrder request
    ) {
        boolean isSuccess = orderService.updateOrderStatus(id, request.status());
        if (isSuccess)
            return ResponseEntity.ok().build();
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @ApiMessage("get orders sorted")
    @GetMapping()
    public ResponseEntity<List<ResponseOrder>> getOrdersSorted(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy
    ) {
        return ResponseEntity.ok(orderService.getOrders(page, size, sortBy));
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @ApiMessage("get Orders By StarReview Or Status")
    @GetMapping("/filter")
    public ResponseEntity<List<ResponseOrder>> getOrdersByStarReviewOrStatus(
            @RequestParam(required = false, name = "star") Integer starReview,
            @RequestParam(required = false, name = "status") StatusOrder status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(orderService.getOrdersByStarReviewOrStatus(starReview, status, page, size));
    }
}
