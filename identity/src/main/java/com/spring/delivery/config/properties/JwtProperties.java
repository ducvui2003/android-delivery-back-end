package com.spring.delivery.config.properties;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;

import com.nimbusds.jose.util.Base64;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Configuration
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JwtProperties {
	@Value("${jwt.base64-secret}")
	String jwtKey;

	@Value("${jwt.access-token-validity-in-seconds}")
	long accessTokenExpiredTime;

	@Value("${jwt.refresh-token-validity-in-seconds}")
	long refreshTokenExpiredTime;

	public static final MacAlgorithm JWT_ALGORITHM = MacAlgorithm.HS256;

	@Bean
	public SecretKey getSecretKey() {
		System.out.println(jwtKey);
		byte[] keyBytes = Base64.from(jwtKey).decode();
		return new SecretKeySpec(keyBytes, 0, keyBytes.length, JWT_ALGORITHM.getName());
	}
}
