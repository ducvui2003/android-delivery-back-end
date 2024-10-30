package com.spring.delivery.util.otp;

public interface OTPGenerator<T> {
	T generateOTP();

	T generateOTP(int length);
}
