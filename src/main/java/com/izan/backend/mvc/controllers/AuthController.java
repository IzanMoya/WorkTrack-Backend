package com.izan.backend.mvc.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.izan.backend.mvc.services.FirebaseAuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private FirebaseAuthService firebaseAuthService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestHeader("Authorization") String token) {
        try {
            FirebaseToken decodedToken = firebaseAuthService.verifyToken(token.replace("Bearer ", ""));
            String uid = decodedToken.getUid();
            String email = decodedToken.getEmail();

            // Enviar una respuesta útil al frontend
            Map<String, Object> response = new HashMap<>();
            response.put("uid", uid);
            response.put("email", email);
            response.put("message", "Token verificado correctamente");

            return ResponseEntity.ok(response);

        } catch (FirebaseAuthException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token inválido: " + e.getMessage());
        }
    }
}

