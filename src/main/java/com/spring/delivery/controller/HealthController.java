package com.spring.delivery.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/health")
@RequiredArgsConstructor
public class HealthController {
    @RequestMapping(value = "/alive", method = RequestMethod.HEAD)
    ResponseEntity<Void> isAlive() {
        return ResponseEntity.ok().build();
    }
}
