package com.izan.backend.mvc.security;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import jakarta.annotation.PostConstruct;
import org.springframework.core.env.Environment;




@Configuration
public class FirebaseConfig {

    private final Environment environment;

    public FirebaseConfig(Environment environment) {
        this.environment = environment;
    }

    @PostConstruct
    public void init() throws IOException {
        InputStream serviceAccount;

        // Detectamos si el perfil activo es "cloud"
        String[] activeProfiles = environment.getActiveProfiles();
        boolean isCloud = false;
        for (String profile : activeProfiles) {
            if (profile.equalsIgnoreCase("cloud")) {
                isCloud = true;
                break;
            }
        }

        if (isCloud) {
            // 🌐 En Render: Leer del entorno codificado en base64
            String base64 = System.getenv("FIREBASE_CONFIG_BASE64");
            if (base64 == null || base64.isEmpty()) {
                throw new IllegalStateException("Variable de entorno FIREBASE_CONFIG_BASE64 no encontrada.");
            }
            byte[] decodedBytes = Base64.getDecoder().decode(base64);
            serviceAccount = new ByteArrayInputStream(decodedBytes);
        } else {
            // 🖥️ En local: Leer desde /resources/firebase/*.json
            serviceAccount = new ClassPathResource("firebase/worktrack-c18a2-firebase-adminsdk-fbsvc-cb3ec24c09.json").getInputStream();
        }

        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        if (FirebaseApp.getApps().isEmpty()) {
            FirebaseApp.initializeApp(options);
        }
    }
}