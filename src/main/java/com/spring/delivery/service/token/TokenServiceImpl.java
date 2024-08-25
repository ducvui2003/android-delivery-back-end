package com.spring.delivery.service.token;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.spring.delivery.service.redis.RedisService;
import com.spring.delivery.util.SecurityUtil;
import com.spring.delivery.util.enums.RedisNameSpace;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TokenServiceImpl implements TokenService {
	SecurityUtil securityUtil;
	RedisService<String> redisService;
	String nameSpace = RedisNameSpace.BLACKLISTED_TOKEN.getName();

	@Override
	public void saveToken(String token, String email) {
		long ttl = securityUtil.remainderTimeToken(token);
		redisService.set(nameSpace + token, email, ttl);
	}

	@Override
	public void deleteToken(String token) {
		redisService.delete(nameSpace + token);
	}

	@Override
	public Optional<String> getToken(String token) {
		return redisService.get(nameSpace + token);
	}
}
