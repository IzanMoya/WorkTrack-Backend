package com.izan.backend.mvc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.izan.backend.mvc.dao.IFichajesDAO;
import com.izan.backend.mvc.entities.Fichajes;


@Service
public class FichajesServiceImpl implements IFichajesService{
	
	@Autowired
	private IFichajesDAO fichajesDAO;
	
	@Override
	public List<Fichajes> findAll() {
		return (List<Fichajes>)fichajesDAO.findAll();
	}
	
	
	@Override
	public void save(Fichajes f) {
		fichajesDAO.save(f);
	}
	
	@Override
	public Fichajes findById(int id) {
		return fichajesDAO.findById(id).orElse(null);
	}
	
	@Override
	public void delete(Fichajes f) {
		fichajesDAO.delete(f);
	}
}
