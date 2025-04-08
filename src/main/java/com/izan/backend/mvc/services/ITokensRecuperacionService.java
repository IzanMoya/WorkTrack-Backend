package com.izan.backend.mvc.services;

import java.util.List;

import com.izan.backend.mvc.entities.TokensRecuperacion;

public interface ITokensRecuperacionService {

	public List<TokensRecuperacion> findAll();
	
	public TokensRecuperacion findById(int id);
	
	public void save(TokensRecuperacion tr);
	
	public void delete(TokensRecuperacion tr);
	
}
