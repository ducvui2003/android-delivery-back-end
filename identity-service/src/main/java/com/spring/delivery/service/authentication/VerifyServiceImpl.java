package com.spring.delivery.service.authentication;

import com.spring.delivery.repository.UserRepository;
import com.spring.delivery.service.email.EmailService;
import com.spring.delivery.service.opt.OTPService;
import com.spring.delivery.service.redis.RedisService;
import com.spring.delivery.util.RedisUtil;
import com.spring.delivery.util.enums.RedisNameSpace;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@Deprecated
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class VerifyServiceImpl implements VerifyService {
	UserRepository userRepository;
	RedisService<String> redisService;
	OTPService otpService;
	EmailService emailService;

	@Override
	public boolean userIsVerify(String email) {
		return redisService.hasKey(RedisUtil.generateKey(RedisNameSpace.OTP_EMAIL_COUNTER, email));
	}

	@Override
	public void verifyOtp(String email, String otp) {
		otpService.verifyOTP(RedisNameSpace.OTP_VERIFY_EMAIL, email, otp);
		userRepository.updateVerifyStatusByEmail(email, true);
	}

	@Override
	public void sendOtp(String email) {
		String otp = otpService.createOPT(RedisNameSpace.OTP_VERIFY_EMAIL, email);
		emailService.sentVerify(email, otp);
	}
}
