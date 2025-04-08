package com.izan.backend.mvc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.izan.backend.mvc.dao.IUbicacionesTrabajoDAO;
import com.izan.backend.mvc.entities.UbicacionesTrabajo;

@Service
public class UbicacionesTrabajoServiceImpl implements IUbicacionesTrabajoService {
	
	@Autowired
	private IUbicacionesTrabajoDAO ubicacionesTrabajoDAO;
	
	@Override
	public List<UbicacionesTrabajo> findAll() {
		return (List<UbicacionesTrabajo>) ubicacionesTrabajoDAO.findAll();
	}
	
	@Override
	public void save (UbicacionesTrabajo ut) {
		ubicacionesTrabajoDAO.save(ut);
	}
	
	@Override
	public UbicacionesTrabajo findById(int id) {
		return ubicacionesTrabajoDAO.findById(id).orElse(null);
	}
	
	@Override
	public void delete(UbicacionesTrabajo ut) {
		ubicacionesTrabajoDAO.delete(ut);
	}
}
