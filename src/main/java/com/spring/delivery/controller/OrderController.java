package com.spring.delivery.controller;

import com.spring.delivery.domain.request.order.RequestOrderCreated;
import com.spring.delivery.domain.response.order.OrderDTO;
import com.spring.delivery.service.order.OrderService;
import com.spring.delivery.util.anotation.ApiMessage;
import com.spring.delivery.util.enums.StatusOrder;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class OrderController {
    OrderService orderService;

    @ApiMessage("add order")
    @PostMapping("/add")
    public ResponseEntity<OrderDTO> addOrder(@RequestBody RequestOrderCreated req){
        return ResponseEntity.ok(orderService.addOrder(req));
    }

    @ApiMessage("update status")
    @PutMapping("/update")
    public ResponseEntity<Integer> updateOrderStatus(
            @RequestParam("id") Long id,
            @RequestParam("status") String status
    ){
        return ResponseEntity.ok(orderService.updateOrderStatus(id, status));
    }

    @ApiMessage("get orders sorted")
    @GetMapping("/get")
    public ResponseEntity<List<OrderDTO>> getOrdersSorted(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy
    ) {
        return ResponseEntity.ok(orderService.getOrders(page, size, sortBy));
    }

    @ApiMessage("get Orders By StarReview Or Status")
    @GetMapping("/filter")
    public ResponseEntity<List<OrderDTO>> getOrdersByStarReviewOrStatus(
            @RequestParam(defaultValue = "-1") int starReview,
            @RequestParam(defaultValue = "ACTIVE") String status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(orderService.getOrdersByStarReviewOrStatus(starReview, StatusOrder.valueOf(status), page, size));
    }
}
