package com.izan.backend.mvc.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.izan.backend.mvc.entities.UbicacionesTrabajo;
import com.izan.backend.mvc.services.IUbicacionesTrabajoService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class UbicacionesTrabajoRestController {

	@Autowired
	private IUbicacionesTrabajoService ubicacionesTrabajoService;
	
	@GetMapping("/ubicacionesTrabajo")
	public List<UbicacionesTrabajo> getAllUbicaciones() {
		return ubicacionesTrabajoService.findAll();
	}
}
