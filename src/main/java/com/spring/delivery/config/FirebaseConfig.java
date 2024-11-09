package com.spring.delivery.config;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.json.gson.GsonFactory;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.firebase.cloud.StorageClient;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.core.io.Resource;
import org.springframework.util.ResourceUtils;

@Configuration
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class FirebaseConfig {
    @Value("${app.service.firebase.bucket}")
    String storageBucket;
    @Value("${app.service.firebase.config-file}")
    Resource firebaseAdminSdk;
    @Value("${app.service.firebase.database-url}")
    String firebaseDatabaseUrl;
    @Value("${app.service.firebase.client-secret-file}")
    Resource clientSecret;

    @Bean
    public FirebaseApp initialize() throws IOException {
        GoogleCredentials credentials = GoogleCredentials
                .fromStream(firebaseAdminSdk.getInputStream());

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(credentials)
                .setDatabaseUrl(firebaseDatabaseUrl)
                .setStorageBucket(storageBucket)
                .build();

        if (FirebaseApp.getApps().isEmpty()) {
            FirebaseApp.initializeApp(options);
        }
        return FirebaseApp.getInstance();
    }


    @Bean
    public Storage storage() {
        return StorageOptions.getDefaultInstance().getService();
    }

    @Bean
    public Bucket bucket(FirebaseApp firebaseApp) {
        return StorageClient.getInstance(firebaseApp).bucket();
    }

    @Bean
    public GoogleClientSecrets googleClientSecrets() throws IOException {
        com.google.api.client.json.JsonFactory jsonFactory = new GsonFactory();
        return GoogleClientSecrets.load(
                jsonFactory, new InputStreamReader(clientSecret.getInputStream()));
    }
}
