package com.izan.backend.mvc.services;

import java.util.List;

import com.izan.backend.mvc.entities.HorariosPredefinidos;

public interface IHorariosPredefinidosService {

	public List<HorariosPredefinidos> findAll();
	
	public HorariosPredefinidos findById(int id);
	
	public void delete(HorariosPredefinidos hp);
	
	public void save(HorariosPredefinidos hp);
}
