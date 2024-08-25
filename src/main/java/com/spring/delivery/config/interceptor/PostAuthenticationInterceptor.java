package com.spring.delivery.config.interceptor;

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.spring.delivery.service.token.TokenService;
import com.spring.delivery.util.SecurityUtil;
import com.spring.delivery.util.exception.AppErrorCode;
import com.spring.delivery.util.exception.AppException;

import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostAuthenticationInterceptor implements HandlerInterceptor {
	TokenService tokenService;

	@Transactional
	@Override
	public boolean preHandle(
			@Nullable HttpServletRequest request, @Nullable HttpServletResponse response, @Nullable Object handler) {
		//		Lấy access token từ request header
		Optional<String> accessTokenRequest = SecurityUtil.getAccessTokenFromRequest(request);
		if (accessTokenRequest.isEmpty()) throw new AppException(AppErrorCode.UNAUTHORIZED);
		//		Kiểm tra access token có tồn tại trong database không
		if (tokenService.getToken(accessTokenRequest.get()).isPresent())
			throw new AppException(AppErrorCode.UNAUTHORIZED);
		return true;
	}
}
