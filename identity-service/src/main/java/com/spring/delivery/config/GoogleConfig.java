package com.spring.delivery.config;

import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStreamReader;

@Configuration
public class GoogleConfig {
    @Value("classpath:client_secret.json")
    private Resource clientSecretsResource;

    @Bean
    public GoogleClientSecrets googleClientSecrets() throws IOException {
        JsonFactory jsonFactory = GsonFactory.builder().setReadLeniency(true).build();
        return GoogleClientSecrets.load(
                jsonFactory, new InputStreamReader(clientSecretsResource.getInputStream()));
    }
}
