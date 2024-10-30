package com.spring.delivery.util.otp;

import java.security.SecureRandom;

import org.springframework.stereotype.Service;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CharOTPGenerator implements OTPGenerator<String> {
	final SecureRandom random = new SecureRandom();
	static final int DEFAULT_LENGTH = 6;
	static final int TYPE_CHAR = 0;
	static final int TYPE_CHAR_UPPER_CASE = 1;

	@Override
	public String generateOTP() {
		return generateOTP(DEFAULT_LENGTH);
	}

	@Override
	public String generateOTP(int length) {
		StringBuilder optBuilder = new StringBuilder();
		for (int i = 0; i < length; i++) {
			int type = random.nextInt(3);
			char c = switch (type) {
				case TYPE_CHAR -> (char) (random.nextInt(26) + 'a');
				case TYPE_CHAR_UPPER_CASE -> (char) (random.nextInt(26) + 'A');
				default -> (char) (random.nextInt(10) + '0');
			};
			optBuilder.append(c);
		}

		return optBuilder.toString();
	}
}
