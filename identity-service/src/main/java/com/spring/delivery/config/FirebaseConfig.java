package com.spring.delivery.config;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class FirebaseConfig {

	@Value("classpath:serviceAccountKey.json")
	private Resource serviceAccountResource;

	@Bean
	public Gson gson() {
		return new GsonBuilder()
				.setLenient()
				.create();
	}

	@Bean
	public FirebaseApp initialize() {
		try {
			InputStream serviceAccount = serviceAccountResource.getInputStream();

			log.info("serviceAccount: {}", serviceAccount.available());
			FirebaseOptions options = new FirebaseOptions.Builder()
					.setCredentials(GoogleCredentials.fromStream(serviceAccount))
					.setDatabaseUrl("https://delivery-react-native-ap-fa468-default-rtdb.firebaseio.com/")
					.build();

			if (FirebaseApp.getApps().isEmpty()) {
				FirebaseApp.initializeApp(options);
				log.info("FirebaseApp initialized successfully");
			}

			return FirebaseApp.getInstance();
		} catch (IOException e) {
			log.error("Failed to initialize FirebaseApp", e);
			return null;
		}
	}
}
