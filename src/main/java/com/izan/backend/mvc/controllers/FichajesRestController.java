package com.izan.backend.mvc.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.izan.backend.mvc.entities.Fichajes;
import com.izan.backend.mvc.services.IFichajesService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/worktrack/fichajes")
public class FichajesRestController {

	@Autowired
	private IFichajesService fichajesService;
	
	@GetMapping("")
	public ResponseEntity<?> getAllFichajes() {
		List<Fichajes> fichajes = fichajesService.findAll();
		if(!fichajes.isEmpty()) {
			return new ResponseEntity<List<Fichajes>>(fichajes, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/{id}")
	public Fichajes getFichajesById(@PathVariable int id) {
		return fichajesService.findById(id);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteFichaje(@PathVariable int id) {
		Fichajes fichaje = fichajesService.findById(id);
		if(fichaje == null) {
			throw new RuntimeException("Fichaje con ID " + id + " no se ha encontrado");
		}
		fichajesService.delete(fichaje);
	}
}
