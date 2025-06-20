package com.izan.backend.mvc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.izan.backend.mvc.dao.IUsuariosDAO;
import com.izan.backend.mvc.entities.Usuarios;
import com.izan.backend.mvc.exceptions.UsuarioNotFoundException;

@Service
public class UsuariosServiceImpl implements IUsuariosService{

	@Autowired
	private IUsuariosDAO usuariosDAO;
	
	@Override
	public List<Usuarios> findAll() {
		return (List<Usuarios>)usuariosDAO.findAll();
	}
	
	@Override
	public void save(Usuarios u) {
		usuariosDAO.save(u);
	}
	
	@Override
	public Usuarios findById(int id) {
		return usuariosDAO.findById(id)
				.orElseThrow(() -> new UsuarioNotFoundException(id));
	}
	
	@Override
	public void delete(Usuarios u) {
		usuariosDAO.delete(u);
	}
	
	@Override
	public Usuarios update(Usuarios u, int id) {
		Usuarios usuarioActual = this.findById(id);
		
		usuarioActual.setNombre(u.getNombre());
		usuarioActual.setApellidos(u.getApellidos());
		usuarioActual.setEmail(u.getEmail());
		usuarioActual.setRol(u.getRol());
		usuarioActual.setTelefono(u.getTelefono());
		usuarioActual.setActividad(u.getActividad());
		usuarioActual.setFechaCreacion(u.getFechaCreacion());
		usuarioActual.setTokensRecuperacions(u.getTokensRecuperacions());
		usuarioActual.setFichajeses(u.getFichajeses());
		usuarioActual.setHorariosPredefinidoses(u.getHorariosPredefinidoses());
		this.save(usuarioActual);
		return usuarioActual;
	}

	@Override
	public Usuarios findByEmail(String email) {
	    return usuariosDAO.findByEmail(email);
	}
}
