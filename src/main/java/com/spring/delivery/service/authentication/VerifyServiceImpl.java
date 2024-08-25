package com.spring.delivery.service.authentication;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.spring.delivery.repository.UserRepository;
import com.spring.delivery.service.email.EmailService;
import com.spring.delivery.service.opt.OTPService;
import com.spring.delivery.service.redis.RedisService;
import com.spring.delivery.util.RedisKeyUtil;
import com.spring.delivery.util.enums.RedisNameSpace;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class VerifyServiceImpl implements VerifyService {
	UserRepository userRepository;
	RedisService<String> redisService;
	OTPService otpService;
	EmailService emailService;

	@Override
	public boolean userIsVerify(String email) {
		return redisService.hasKey(RedisKeyUtil.generateKey(RedisNameSpace.OTP_EMAIL_COUNTER, email));
	}

	@Override
	public void verifyOtp(String email, String otp) {
		otpService.verifyOTP(RedisNameSpace.OTP_VERIFY_EMAIL, email, otp);
		userRepository.updateVerifyStatusByEmail(email, true);
	}

	@Override
	public void sendOtp(String email) {
		String otp = otpService.createOPT(RedisNameSpace.OTP_VERIFY_EMAIL, email);
		Map<String, Object> templateModel = new HashMap<>();
		templateModel.put("code", otp.split(""));
		templateModel.put("email", email);
		emailService.sent(email, "Verify Email", templateModel);
	}
}
