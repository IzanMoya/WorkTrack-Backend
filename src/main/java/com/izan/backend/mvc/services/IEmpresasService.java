package com.izan.backend.mvc.services;

import java.util.List;

import com.izan.backend.mvc.entities.Empresas;

public interface IEmpresasService {

	public List<Empresas> findAll();
	
	public Empresas findById(int id);
	
	public void delete(Empresas e);
	
	public void save(Empresas e);
	
	public Empresas update(Empresas e, int id);
}
