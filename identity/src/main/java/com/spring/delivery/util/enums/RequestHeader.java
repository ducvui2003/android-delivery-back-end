package com.spring.delivery.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum RequestHeader {
	EMAIL("phoneNumber");

	private String name;
}
