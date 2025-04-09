package com.izan.backend.mvc.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.izan.backend.mvc.entities.UbicacionesTrabajo;
import com.izan.backend.mvc.services.IUbicacionesTrabajoService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/worktrack/ubicacionesTrabajo")
public class UbicacionesTrabajoRestController {

	@Autowired
	private IUbicacionesTrabajoService ubicacionesTrabajoService;
	
	@GetMapping("")
	public List<UbicacionesTrabajo> getAllUbicaciones() {
		return ubicacionesTrabajoService.findAll();
	}
	
	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public UbicacionesTrabajo create(@RequestBody UbicacionesTrabajo ubicaciones) {
		ubicacionesTrabajoService.save(ubicaciones);
		return ubicaciones;
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUbicacion(@PathVariable int id) {
		UbicacionesTrabajo ubicaciones = ubicacionesTrabajoService.findById(id);
		if(ubicaciones == null ) {
			throw new RuntimeException("Ubicaciones con ID " + id + " no se ha encontrado.");
		}
		ubicacionesTrabajoService.delete(ubicaciones);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public UbicacionesTrabajo update(@RequestBody UbicacionesTrabajo ut, @PathVariable int id) {
		return ubicacionesTrabajoService.update(ut, id);
	}
	
}
