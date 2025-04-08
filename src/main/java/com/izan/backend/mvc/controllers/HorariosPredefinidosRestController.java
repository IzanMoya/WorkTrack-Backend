package com.izan.backend.mvc.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.izan.backend.mvc.entities.HorariosPredefinidos;
import com.izan.backend.mvc.services.IHorariosPredefinidosService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class HorariosPredefinidosRestController {

	@Autowired
	private IHorariosPredefinidosService horariosPredefinidosService;
	
	@GetMapping("/horarios")
	public List<HorariosPredefinidos> getAllHorarios() {
		return horariosPredefinidosService.findAll();
	}
	
}
