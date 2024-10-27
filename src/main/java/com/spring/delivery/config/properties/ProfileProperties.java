package com.spring.delivery.config.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Getter
@Component
public class ProfileProperties {
	@Value("${spring.profiles.active}")
	private String activeProfile;

	public boolean isDevEnvironment() {
		return "dev".equals(activeProfile);
	}

	public boolean isProdEnvironment() {
		return "prod".equals(activeProfile);
	}
}
