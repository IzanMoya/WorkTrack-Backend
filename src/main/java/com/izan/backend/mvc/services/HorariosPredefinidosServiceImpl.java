package com.izan.backend.mvc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.izan.backend.mvc.dao.IHorariosPredefinidosDAO;
import com.izan.backend.mvc.entities.HorariosPredefinidos;

@Service
public class HorariosPredefinidosServiceImpl implements IHorariosPredefinidosService{

	@Autowired
	private IHorariosPredefinidosDAO horariospredefinidosDAO;
	
	@Override
	public List<HorariosPredefinidos> findAll() {
		return (List<HorariosPredefinidos>) horariospredefinidosDAO.findAll();
	}
	
	@Override
	public void save (HorariosPredefinidos hp) {
		horariospredefinidosDAO.save(hp);
	}
	
	@Override
	public HorariosPredefinidos findById(int id) {
		return horariospredefinidosDAO.findById(id).orElse(null);
	}
	
	@Override
	public void delete(HorariosPredefinidos hp) {
		horariospredefinidosDAO.delete(hp);
	}
	
}
