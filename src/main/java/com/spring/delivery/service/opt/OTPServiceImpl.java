/**
 * Nguyen Dinh Lam
 * Email: kiminonawa1305@gmail.com
 * Phone number: +84 855354919
 * Create at: 10:03 AM - 23/08/2024
 * User: lam-nguyen
 **/

package com.spring.delivery.service.opt;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.spring.delivery.service.redis.RedisService;
import com.spring.delivery.util.RedisUtil;
import com.spring.delivery.util.enums.RedisNameSpace;
import com.spring.delivery.util.exception.AppErrorCode;
import com.spring.delivery.util.exception.AppException;
import com.spring.delivery.util.otp.OTPGenerator;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class OTPServiceImpl implements OTPService {
	RedisService<String> redisService;
	OTPGenerator<String> charOTPGenerator;

	@Value("${app.otp.max-try}")
	@NonFinal
	int maxTry;

	@Value("${app.otp.expiration}")
	@NonFinal
	long timeout;

	@Value("${app.otp.length}")
	@NonFinal
	int length;

	@Value("${app.otp.resend.max-try}")
	@NonFinal
	int maxResend;

	@Value("${app.otp.resend.expiration}")
	@NonFinal
	int resendTime;


	static int INIT_COUNTER = 1;


	public void verifyOTP(RedisNameSpace nameSpace, String email, String otp) {
		String otpKey = RedisUtil.generateKey(nameSpace, email);
		String verifyCounterKey = RedisUtil.generateKey(nameSpace, RedisNameSpace.VERIFY_COUNTER.getName(), email);

		Optional<String> otpOptional = redisService.get(otpKey);
		Optional<String> verifyCounterOptional = redisService.get(verifyCounterKey);

		// TH: OTP không đúng
		if (otpOptional.isEmpty() || verifyCounterOptional.isEmpty())
			throw new AppException(AppErrorCode.OTP_NOT_MATCH);

		// TH: Đã xác thực OTP quá số lần cho phép
		if (Integer.parseInt(verifyCounterOptional.get()) > maxTry) throw new AppException(AppErrorCode.MAX_TRY);

		if (!otpOptional.get().equals(otp)) {
			redisService.increment(verifyCounterKey);
			throw new AppException(AppErrorCode.OTP_NOT_MATCH);
		}

		String resendCounterKey = RedisUtil.generateKey(nameSpace, RedisNameSpace.RESEND_COUNTER.getName(), email);
		// Xóa OTP và số lần nhập OTP trong redis
		redisService.delete(otpKey);
		redisService.delete(verifyCounterKey);
		redisService.delete(resendCounterKey);
	}

	@Override
	public String createOPT(RedisNameSpace nameSpace, String email) {
		String code = charOTPGenerator.generateOTP(length);
		String optKey = RedisUtil.generateKey(nameSpace, email);
		String verifyCounterKey = RedisUtil.generateKey(nameSpace, RedisNameSpace.VERIFY_COUNTER.getName(), email);
		String resendOptCounterKey = RedisUtil.generateKey(nameSpace, RedisNameSpace.RESEND_COUNTER.getName(), email);
		Optional<String> resendCouterOptional = redisService.get(resendOptCounterKey);
		if (redisService.hasKey(optKey) && resendCouterOptional.isPresent()) {
			int counter = Integer.parseInt(resendCouterOptional.get());
			if (counter > maxResend) throw new AppException(AppErrorCode.MAX_TRY);

			redisService.increment(resendOptCounterKey);
		}else{
			redisService.set(resendOptCounterKey, String.valueOf(INIT_COUNTER), resendTime);
		}

		redisService.set(optKey, code, timeout);
		redisService.set(verifyCounterKey, String.valueOf(INIT_COUNTER), resendTime);
		return code;
	}


}
