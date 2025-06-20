package com.izan.backend.mvc.services;

import java.util.Comparator;
import java.util.Date;
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
	public Fichajes findFichajeDeHoy(int usuarioId, Date hoy) {
	    List<Fichajes> fichajes = fichajesDAO.findFichajesDeHoy(usuarioId);
	    if (fichajes.isEmpty()) return null;

	    // Puedes ordenar si lo necesitas por fecha
	    fichajes.sort(Comparator.comparing(Fichajes::getFechaInicio));
	    return fichajes.get(fichajes.size() - 1); // el último fichaje del día
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

	@Override
	public List<Fichajes> findByEmpresaId(int empresaId) {
	    return fichajesDAO.findByEmpresaId(empresaId); // ✅ Ya está bien definido
	}

}
