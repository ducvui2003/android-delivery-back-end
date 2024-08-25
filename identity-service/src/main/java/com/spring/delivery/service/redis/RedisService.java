package com.spring.delivery.service.redis;

import java.time.Duration;
import java.util.Optional;

import com.spring.delivery.util.enums.RedisNameSpace;

import io.lettuce.core.RedisException;

public interface RedisService<T> {
	void set(String key, T data);

	void set(String key, T data, long timeoutInSeconds);

	void set(String key, T data, Duration duration);

	Optional<T> get(String key);

	void delete(String key);

	boolean hasKey(String key);

	void deleteKeysByNameSpace(RedisNameSpace nameSpace);

	void deleteKeysByNameSpace(String nameSpace);

	void increment(String key) throws RedisException;

	void decrement(String key) throws RedisException;
}
