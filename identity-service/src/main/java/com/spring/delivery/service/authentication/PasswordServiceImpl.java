package com.spring.delivery.service.authentication;

import com.spring.delivery.model.User;
import com.spring.delivery.repository.UserRepository;
import com.spring.delivery.service.email.EmailService;
import com.spring.delivery.service.opt.OTPService;
import com.spring.delivery.util.enums.RedisNameSpace;
import com.spring.delivery.util.exception.AppErrorCode;
import com.spring.delivery.util.exception.AppException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PasswordServiceImpl implements PasswordService {
	OTPService otpService;
	UserRepository userRepository;
	PasswordEncoder passwordEncoder;
	EmailService emailService;

	@Override
	public void resetPassword(String otp, String email, String newPassword) {
		otpService.verifyOTP(RedisNameSpace.OTP_RESET_PASSWORD, email, otp);
		userRepository.updatePasswordByEmail(email, passwordEncoder.encode(newPassword));
	}

	@Override
	public void sendOtp(String email) {
		String otp = otpService.createOPT(RedisNameSpace.OTP_RESET_PASSWORD, email);
		emailService.sentResetPassword(email, otp);
	}

	@Override
	public void changePassword(String email, String oldPassword, String newPassword) {
		Optional<User> user = userRepository.findByEmail(email);
		if (user.isEmpty() || !passwordEncoder.matches(oldPassword, user.get().getPassword()))
			throw new AppException(AppErrorCode.PASSWORD_NOT_MATCH);
		userRepository.updatePasswordByEmail(email, passwordEncoder.encode(newPassword));
	}
}
