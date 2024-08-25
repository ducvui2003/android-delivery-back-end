package com.spring.delivery.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum RedisNameSpace {
	BLACKLISTED_TOKEN("blacklisted:token:"),
	OTP_EMAIL("otp_email:"),
	OTP_EMAIL_COUNTER("otp_email_counter:"),
	OTP_RESET_PASSWORD("otp_reset_password:"),
	OTP_RESET_PASSWORD_COUNTER("otp_reset_password_counter:"),
	OTP_VERIFY_EMAIL("otp_verify_email:"),
	RESEND_COUNTER("resend_counter"),
	VERIFY_COUNTER("verify_counter"),
	;

	private String name;
}
