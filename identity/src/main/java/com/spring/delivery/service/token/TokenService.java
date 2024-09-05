package com.spring.delivery.service.token;

import java.util.Optional;

public interface TokenService {
	void saveToken(String token, String email);

	void deleteToken(String token);

	Optional<String> getToken(String token);
}
