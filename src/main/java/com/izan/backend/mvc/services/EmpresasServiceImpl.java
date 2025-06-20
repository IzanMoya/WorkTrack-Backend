package com.izan.backend.mvc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.izan.backend.mvc.dao.IEmpresasDAO;
import com.izan.backend.mvc.entities.Empresas;

@Service
public class EmpresasServiceImpl implements IEmpresasService{

	@Autowired
	private IEmpresasDAO empresasDAO;
	
	@Override
	public List<Empresas> findAll() {
		return (List<Empresas>) empresasDAO.findAll();
	}
	
	@Override
	public void save (Empresas e) {
		empresasDAO.save(e);
	}
	
	@Override
	public Empresas findById(int id) {
		return empresasDAO.findById(id).orElse(null);
	}
	
	@Override
	public void delete(Empresas e) {
		empresasDAO.delete(e);
	}

	@Override
	public Empresas update(Empresas e, int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Empresas findByNombre(String nombre) {
	    return empresasDAO.findByNombreIgnoreCase(nombre); // o findByNombreIgnoreCase si lo llamaste así
	}
}
