package com.izan.backend.mvc.security;

import java.io.FileInputStream;
import java.io.IOException;

import org.springframework.context.annotation.Configuration;

import com.google.api.client.util.Value;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import jakarta.annotation.PostConstruct;

@Configuration
public class FirebaseConfig {

	@Value("${firebase.config.path}")
	private String firebaseConfigPath;
	
	@PostConstruct
	public void init() throws IOException {
	    FileInputStream serviceAccount = new FileInputStream(firebaseConfigPath);

	    FirebaseOptions options = FirebaseOptions.builder()
	            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
	            .build();

	    if (FirebaseApp.getApps().isEmpty()) {
	        FirebaseApp.initializeApp(options);
	    }
	}
}
