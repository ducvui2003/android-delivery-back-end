package com.spring.delivery.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class HelloController {
	@GetMapping("/hello")
	public ResponseEntity<Integer> hello() {
		return ResponseEntity.ok(123);
	}
}
