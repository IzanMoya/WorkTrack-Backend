package com.izan.backend.mvc.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.izan.backend.mvc.entities.Fichajes;
import com.izan.backend.mvc.entities.Usuarios;
import com.izan.backend.mvc.services.IFichajesService;
import com.izan.backend.mvc.services.IUsuariosService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/worktrack/usuarios")
public class UsuariosRestController {

	@Autowired
	private IUsuariosService usuariosService;
	
	@Autowired 
	private IFichajesService fichajesService;
	
	//GET de todos los usuarios
	@GetMapping("")
	public ResponseEntity<?> getAllUsuarios() {
		List<Usuarios> usuarios = usuariosService.findAll();
		if(!usuarios.isEmpty()) {
			//Si hay datos enviará el List<Usuarios>
			return new ResponseEntity<List<Usuarios>>(usuarios, HttpStatus.OK);
		}
		//Devuelve el ResponseEntity.noContent().build()
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
	}
	
	//CREATE de un Usuario
	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public Usuarios create(@RequestBody Usuarios usuario) {
		usuariosService.save(usuario);
		return usuario;
	}
	
	//GET de un usario por su ID
	@GetMapping("/{id}")
	public Usuarios getUsuarioById(@PathVariable int id) {
		return usuariosService.findById(id);
	}
	
	//GET de usuario por su Nombre
	@GetMapping("/name/{name}")
	public Usuarios getUsuarioByName(@PathVariable String nombre) {
		Usuarios aux = new Usuarios();
		for (Usuarios u : usuariosService.findAll()) {
			if(u.getNombre().equals(nombre)) {
				aux = u;
				return usuariosService.findById(aux.getId());
			}
		}
		return null;
	}
	
	//DELETE de usuario
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUsuario(@PathVariable int id) {
		Usuarios usuario = usuariosService.findById(id);
		if(usuario == null) {
			throw new RuntimeException("Usuarios con ID " + id + " no se ha encontrado.");
		}
		usuariosService.delete(usuario);
	}
	
	//UPDATE de usuario por su ID
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Usuarios update(@RequestBody Usuarios u, @PathVariable int id) {
		return usuariosService.update(u, id);
	}
	
	//GET Fichajes de Usuario por su ID
	@GetMapping("{id}/fichajes")
	public List<Fichajes> getAllFichajesOfUsuario(@PathVariable int id) {
		Usuarios usuarios = usuariosService.findById(id);
		if(usuarios == null) {
			throw new RuntimeException("Usuarios con ID " + id + " no encontrado." );
		}
		return new ArrayList<>(usuarios.getFichajeses());
	}
	
	//DELETE Fichajes de un Usuario
	@DeleteMapping("{id_u}/fichajes/{id_f}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteFichajesByUsuario(@PathVariable int id_u, @PathVariable String id_f) {
		id_f = id_f.replace("-", "/");
		Fichajes fichajes = fichajesService.findById(id_u);
		if (fichajes == null) {
			throw new RuntimeException("Fichajes no encontrados para el Usuarios con ID " + id_u);
		}
		fichajesService.delete(fichajes);
	}
	
//	//UPDATE de fichajes para un usuario
//	@PutMapping("{id_u}/fichajes/{id_f}")
//	public Fichajes updateFichajesByUsuario(@PathVariable int id_u, @PathVariable String id_f, @RequestBody Fichajes updateFichajes) {
//		id_f = id_f.replace("-", "/");
//		Fichajes fichajesActuales = fichajesService.findById(id_u);
//		if(fichajesActuales == null) {
//			throw new RuntimeException("Fichajes no encontrados para el Usuario con ID " + id_u);
//		}
//		fichajesActuales.setUsuarios(updateFichajes.getUsuarios());
//		fichajesActuales.setTipo(updateFichajes.getTipo());
//		fichajesActuales.setLatitud(updateFichajes.getLatitud());
//		fichajesActuales.setLongitud(updateFichajes.getLongitud());
//		fichajesActuales.setFecha(updateFichajes.getFecha());
//		return fichajesService.save(fichajesActuales);
//	}
	
	//Cuando Intentamos hacer un POST a un usuario con Datos Incorrectos
	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> handleException(HttpMessageNotReadableException notReadable) {
		Map<String, Object> response = new HashMap<>();
		response.put("status", "400 - BAD REQUEST");
		response.put("message", "Invalid or malformed JSON data");
		response.put("error", notReadable.getMessage());
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
	}
}

