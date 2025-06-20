package com.izan.backend.mvc.services;

import java.util.Date;
import java.util.List;

import com.izan.backend.mvc.entities.Fichajes;

public interface IFichajesService {
	
	public List<Fichajes> findAll();
	
	public Fichajes findById(int id);
	
	public void save(Fichajes f);
	
	public void delete(Fichajes f);
	
	public Fichajes findFichajeDeHoy(int usuarioId, Date hoy);

	List<Fichajes> findByEmpresaId(int empresaId);
	

}
