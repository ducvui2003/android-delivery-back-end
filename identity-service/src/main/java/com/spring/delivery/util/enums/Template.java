package com.spring.delivery.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum Template {
	VERIFY_EMAIL("phoneNumber-verify"),
	;

	private String name;
}
