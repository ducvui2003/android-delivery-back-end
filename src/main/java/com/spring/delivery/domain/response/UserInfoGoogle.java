package com.spring.delivery.domain.response;

public record UserInfoGoogle(
		String sub,
		String name,
		String given_name,
		String family_name,
		String picture,
		String email,
		boolean email_verified) {}
