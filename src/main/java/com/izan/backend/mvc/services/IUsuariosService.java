package com.izan.backend.mvc.services;

import java.util.List;

import com.izan.backend.mvc.entities.Usuarios;

public interface IUsuariosService {
	
	public List<Usuarios> findAll();
	
	public Usuarios findById(int id);
	
	public void delete(Usuarios u);
	
	public void save(Usuarios u);
	
	public Usuarios update(Usuarios u, int id);
	
	Usuarios findByEmail(String email);
}
