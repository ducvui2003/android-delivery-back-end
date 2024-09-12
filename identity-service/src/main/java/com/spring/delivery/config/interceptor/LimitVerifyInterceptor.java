package com.spring.delivery.config.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.spring.delivery.repository.UserRepository;
import com.spring.delivery.service.redis.RedisService;
import com.spring.delivery.util.RedisUtil;
import com.spring.delivery.util.enums.RedisNameSpace;
import com.spring.delivery.util.enums.RequestHeader;
import com.spring.delivery.util.exception.AppErrorCode;
import com.spring.delivery.util.exception.AppException;

import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LimitVerifyInterceptor implements HandlerInterceptor {
	RedisService<String> redisService;
	UserRepository userRepository;

	@Override
	public boolean preHandle(HttpServletRequest request, @Nullable HttpServletResponse response, @Nullable Object handler) {
		String email = request.getHeader(RequestHeader.PHONE_NUMBER.getName());
		String otpKey = RedisUtil.generateKey(RedisNameSpace.OTP_VERIFY_EMAIL, email);

		// TH: Chưa đăng ký mà xác thực OTP
		if (!redisService.hasKey(otpKey) || !userRepository.existsByEmail(email))
			throw new AppException(AppErrorCode.USER_NOT_FOUND);

		return true;
	}
}
