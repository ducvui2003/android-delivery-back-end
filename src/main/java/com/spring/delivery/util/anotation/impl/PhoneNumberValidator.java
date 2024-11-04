package com.spring.delivery.util.anotation.impl;

import com.spring.delivery.util.anotation.NumberPhone;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<NumberPhone, String> {
    //regex cho số điện thoại Việt Nam
    private static final String PHONE_NUMBER_PATTERN = "^((\\+84)|0)(3[2-9]|5[6|8|9]|7[0|6-9]|8[1-5]|9[0-9])[0-9]{7}$";

    @Override
    public void initialize(NumberPhone constraintAnnotation) {
    }

    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext context) {
        if (phoneNumber == null) {
            return false;
        }
        return phoneNumber.matches(PHONE_NUMBER_PATTERN);
    }
}
