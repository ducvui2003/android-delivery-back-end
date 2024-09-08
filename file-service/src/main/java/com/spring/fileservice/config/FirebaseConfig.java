package com.spring.fileservice.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Acl;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.StorageClient;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Configuration
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
@Slf4j
public class FirebaseConfig {
    @Value("${app.service.firebase.bucket}")
    String storageBucket;
    @Value("classpath:firebase.json")
    Resource firebaseResource;

    @PostConstruct
    public void initialize() {
        try {
            InputStream serviceAccount = firebaseResource.getInputStream();
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount)).build();

            FirebaseApp.initializeApp(options);
            log.info("Firebase app has been initialized");
        } catch (IOException e) {
            log.error("Firebase app has not been initialized");
        }
    }

    @Bean
    public Storage storage() {
        return StorageOptions.getDefaultInstance().getService();
    }

    @Bean
    public Bucket bucket() {
        return StorageClient.getInstance().bucket(storageBucket);
    }
}
