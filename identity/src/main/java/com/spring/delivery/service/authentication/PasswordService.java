package com.spring.delivery.service.authentication;

public interface PasswordService {
	void resetPassword(String otp, String email, String newPassword);

	void sendOtp(String email);

	void changePassword(String email, String oldPassword, String newPassword);
}
