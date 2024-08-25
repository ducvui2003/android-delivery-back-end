package com.spring.delivery.config.properties;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.json.gson.GsonFactory;

import lombok.Data;

@Data
@Component("securityProperties")
@ConfigurationProperties(prefix = "spring.security.allowed")
public class SecurityProperties {
	private List<String> origins;
	private List<String> methods;
	private List<String> headers;

	@Bean
	public GoogleClientSecrets googleClientSecrets() throws IOException {
		com.google.api.client.json.JsonFactory jsonFactory = new GsonFactory();
		return GoogleClientSecrets.load(
				jsonFactory, new FileReader(ResourceUtils.getFile("classpath:client_secret.json")));
	}
}
