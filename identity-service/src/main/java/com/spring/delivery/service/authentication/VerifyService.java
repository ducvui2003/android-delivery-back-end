package com.spring.delivery.service.authentication;

public interface VerifyService {
	boolean userIsVerify(String email);

	void sendOtp(String email);

	void verifyOtp(String email, String code);
}
