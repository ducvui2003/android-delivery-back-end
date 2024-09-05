package com.spring.notificationservice.util.anotation.impl;

import com.spring.notificationservice.util.anotation.EnumValid;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class EnumValidator implements ConstraintValidator<EnumValid, String> {
	private Class<? extends Enum<?>> enumClass;

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null || value.isEmpty()) return false; // handle null value separately if needed

		return Arrays.stream(enumClass.getEnumConstants())
				.anyMatch(e -> e.name().equals(value));
	}

	@Override
	public void initialize(EnumValid constraintAnnotation) {
		this.enumClass = constraintAnnotation.enumClass();
	}
}
