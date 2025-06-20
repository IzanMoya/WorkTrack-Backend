package com.izan.backend.mvc.security;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Base64;

import org.springframework.context.annotation.Configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import jakarta.annotation.PostConstruct;

@Configuration
public class FirebaseConfig {

    @PostConstruct
    public void init() throws Exception {
        String base64 = System.getenv("FIREBASE_CONFIG_BASE64");
        if (base64 == null || base64.isEmpty()) {
            throw new IllegalStateException("No se encontró la variable de entorno FIREBASE_CONFIG_BASE64");
        }

        byte[] decodedBytes = Base64.getDecoder().decode(base64);
        InputStream serviceAccount = new ByteArrayInputStream(decodedBytes);

        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        if (FirebaseApp.getApps().isEmpty()) {
            FirebaseApp.initializeApp(options);
        }
    }
}

