package com.mrgoropeza.portfoliobackend.conf;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.io.Resource;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.StorageClient;

import lombok.Data;

@Configuration
@ConfigurationProperties(prefix = "firebase")
@Data
public class FirebaseConfig {

    @Value(value = "classpath:firebase.json")
    private Resource serviceAccountResource;

    private String bucketName;

    private String imageUrl;

    @Bean
    public FirebaseApp createFireBaseApp() throws IOException {
        InputStream serviceAccount = serviceAccountResource.getInputStream();

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setStorageBucket(bucketName)
                .build();

        if (FirebaseApp.getInstance() != null) {
            return FirebaseApp.getInstance();
        }
        return FirebaseApp.initializeApp(options);
    }

    @Bean
    @DependsOn(value = "createFireBaseApp")
    public StorageClient createFirebaseStorage() {
        return StorageClient.getInstance();
    }

    @Bean
    public String getImageUrl() {
        return imageUrl;
    }

}
