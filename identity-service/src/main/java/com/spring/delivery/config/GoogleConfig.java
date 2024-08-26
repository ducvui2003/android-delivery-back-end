package com.spring.delivery.config;

import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.json.gson.GsonFactory;

@Configuration
public class GoogleConfig {
	@Value("classpath:client_secret.json")
	private Resource clientSecretsResource;

	@Bean
	public GoogleClientSecrets googleClientSecrets() throws IOException {
		com.google.api.client.json.JsonFactory jsonFactory = new GsonFactory();
		return GoogleClientSecrets.load(
				jsonFactory, new InputStreamReader(clientSecretsResource.getInputStream()));
	}
}
