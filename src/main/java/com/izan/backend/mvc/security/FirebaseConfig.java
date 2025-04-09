package com.izan.backend.mvc.security;

import java.io.InputStream;

import org.springframework.context.annotation.Configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import jakarta.annotation.PostConstruct;

@Configuration
public class FirebaseConfig {

    @PostConstruct
    public void init() throws Exception {
        InputStream serviceAccount = getClass().getClassLoader().getResourceAsStream("firebase/worktrack-c18a2-firebase-adminsdk-fbsvc-cb3ec24c09.json");

        if (serviceAccount == null) {
            throw new IllegalStateException("No se pudo cargar el archivo firebase-key.json desde el classpath.");
        }

        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        if (FirebaseApp.getApps().isEmpty()) {
            FirebaseApp.initializeApp(options);
        }
    }
}
