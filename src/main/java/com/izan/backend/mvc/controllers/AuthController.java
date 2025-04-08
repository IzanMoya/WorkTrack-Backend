package com.izan.backend.mvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@PostMapping("/verify")
	public FirebaseToken verifyToken(@RequestHeader("Authorization") String token) throws FirebaseAuthException {
		return firebaseAuthService.verifyToken(token.replace("Bearer" , ""));
	}
}
