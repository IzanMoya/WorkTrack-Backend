package com.izan.backend.mvc.services;

import java.util.List;

import com.izan.backend.mvc.entities.UbicacionesTrabajo;

public interface IUbicacionesTrabajoService {

	public List<UbicacionesTrabajo> findAll();
	
	public UbicacionesTrabajo findById(int id);
	
	public void delete(UbicacionesTrabajo ut);
	
	public void save(UbicacionesTrabajo ut);
}
