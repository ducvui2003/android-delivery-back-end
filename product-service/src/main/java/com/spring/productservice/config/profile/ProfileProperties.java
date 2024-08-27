package com.spring.productservice.config.profile;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

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
