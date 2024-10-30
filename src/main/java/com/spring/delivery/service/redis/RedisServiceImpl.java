package com.spring.delivery.service.redis;

import java.time.Duration;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.spring.delivery.util.enums.RedisNameSpace;

import io.lettuce.core.RedisException;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class RedisServiceImpl<T> implements RedisService<T> {
	private final RedisTemplate<String, T> redisTemplate;

	@Override
	public void set(String key, T data) {
		redisTemplate.opsForValue().set(key, data);
	}

	@Override
	public void set(String key, T data, long timeoutInSeconds) {
		redisTemplate.opsForValue().set(key, data, Duration.ofSeconds(timeoutInSeconds));
	}

	@Override
	public void set(String key, T data, Duration duration) {
		redisTemplate.opsForValue().set(key, data, duration);
	}

	@Override
	public Optional<T> get(String key) {
		return Optional.ofNullable(redisTemplate.opsForValue().get(key));
	}

	@Override
	public void delete(String key) {
		redisTemplate.delete(key);
	}

	@Override
	public boolean hasKey(String key) {
		return Boolean.TRUE.equals(redisTemplate.hasKey(key));
	}

	@Override
	public void deleteKeysByNameSpace(RedisNameSpace nameSpace) {
		this.deleteKeysByNameSpace(nameSpace.getName());
	}

	@Override
	public void deleteKeysByNameSpace(String nameSpace) {
		String pattern = nameSpace + "*";
		Set<String> keys = redisTemplate.keys(pattern);

		if (keys != null && !keys.isEmpty()) {
			redisTemplate.delete(keys);
		}
	}

	@Override
	public void increment(String key) throws RedisException {
		redisTemplate.opsForValue().increment(key);
	}

	@Override
	public void decrement(String key) throws RedisException {
		redisTemplate.opsForValue().decrement(key);
	}
}
