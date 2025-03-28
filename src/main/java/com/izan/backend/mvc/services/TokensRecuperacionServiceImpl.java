package com.izan.backend.mvc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.izan.backend.mvc.dao.ITokensRecuperacionDAO;
import com.izan.backend.mvc.entities.TokensRecuperacion;

@Service
public class TokensRecuperacionServiceImpl implements ITokensRecuperacionService{

	@Autowired
	private ITokensRecuperacionDAO tokensrecuperacionDAO;
	
	@Override
	public List<TokensRecuperacion> findAll() {
		return (List<TokensRecuperacion>)tokensrecuperacionDAO.findAll();
	}
	
	@Override
	public void save(TokensRecuperacion tr) {
		tokensrecuperacionDAO.save(tr);
	}
	
	@Override
	public TokensRecuperacion findById(int id) {
		return tokensrecuperacionDAO.findById(id).orElse(null);
	}
	
	@Override
	public void delete(TokensRecuperacion tr) {
		tokensrecuperacionDAO.delete(tr);
	}
	
}
