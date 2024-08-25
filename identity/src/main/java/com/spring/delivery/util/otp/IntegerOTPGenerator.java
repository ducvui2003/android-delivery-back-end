package com.spring.delivery.util.otp;

import java.security.SecureRandom;

public class IntegerOTPGenerator implements OTPGenerator<Integer> {
	private final SecureRandom random = new SecureRandom();
	private static final int DEFAULT_LENGTH = 6;

	@Override
	public Integer generateOTP() {
		return generateOTP(DEFAULT_LENGTH);
	}

	@Override
	public Integer generateOTP(int length) {
		return (int) Math.pow(10, length - 1) + random.nextInt((int) Math.pow(10, length - 2));
	}
}
