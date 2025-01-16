package com.spring.delivery.controller;

import com.spring.delivery.domain.request.order.RequestOrderDetailCreated;
import com.spring.delivery.domain.response.order.ResponseOrderDetail;
import com.spring.delivery.service.order.OrderDetailService;
import com.spring.delivery.util.anotation.ApiMessage;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/order-detail")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderDetailController {
    OrderDetailService orderDetailService;

    @ApiMessage("create order detail")
    @PostMapping("/add")
    public ResponseEntity<RequestOrderDetailCreated> addOrderDetail( @RequestBody RequestOrderDetailCreated req) {
        log.info("OrderDetailController.addOrderDetail");
        return ResponseEntity.ok(orderDetailService.addOrderDetail(req));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseOrderDetail> getOrderDetailById(@PathVariable Long id) {
        log.info("OrderDetailController.getOrderDetailById");
        return ResponseEntity.ok(orderDetailService.getOrderDetailById(id));
    }
}
