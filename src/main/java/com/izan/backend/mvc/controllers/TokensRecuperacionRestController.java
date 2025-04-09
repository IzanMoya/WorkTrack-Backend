package com.izan.backend.mvc.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.izan.backend.mvc.entities.TokensRecuperacion;
import com.izan.backend.mvc.services.ITokensRecuperacionService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/worktrack")
public class TokensRecuperacionRestController {

	@Autowired
	private ITokensRecuperacionService tokensrecuperacionService;
	
	@GetMapping("/tokensrecuperacion")
	public List<TokensRecuperacion> getAllTokens() {
		return tokensrecuperacionService.findAll();
	}
}
