package com.izan.backend.mvc.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	
	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public Fichajes create(@RequestBody Fichajes fichaje) {
	    fichajesService.save(fichaje);

	    Fichajes fichajeGuardado = fichajesService.findById(fichaje.getId());
	    System.out.println(">>> Fichaje guardado: " + fichajeGuardado);

	    return fichajeGuardado;
	}

	@GetMapping("/ultimo/{usuarioId}")
	public Fichajes getUltimoFichajeDeHoy(@PathVariable int usuarioId) {
	    return fichajesService.findFichajeDeHoy(usuarioId, new Date());
	}
	
	@PutMapping("/{id}")
	public Fichajes update(@PathVariable int id, @RequestBody Fichajes datos) {
	    Fichajes existente = fichajesService.findById(id);
	    if (existente == null) {
	        throw new RuntimeException("No se encontró el fichaje");
	    }

	    existente.setLatitudFin(datos.getLatitudFin());
	    existente.setLongitudFin(datos.getLongitudFin());
	    existente.setFechaFin(datos.getFechaFin());

	    fichajesService.save(existente);
	    return existente;
	}
	
	@GetMapping("/empresa/{empresaId}")
	public ResponseEntity<?> getFichajesByEmpresa(@PathVariable int empresaId) {
	    List<Fichajes> fichajes = fichajesService.findByEmpresaId(empresaId);
	    if (!fichajes.isEmpty()) {
	        return new ResponseEntity<>(fichajes, HttpStatus.OK);
	    }
	    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/descanso/{id}")
	public Fichajes actualizarDescanso(@PathVariable int id, @RequestBody Fichajes datos) {
	    Fichajes fichaje = fichajesService.findById(id);
	    if (fichaje == null) throw new RuntimeException("Fichaje no encontrado");

	    // Alternar entre inicio y fin
	    if (!fichaje.isEnDescanso()) {
	        fichaje.setFechaInicioDescanso(new Date());
	        fichaje.setEnDescanso(true);
	    } else {
	        fichaje.setFechaFinDescanso(new Date());
	        fichaje.setEnDescanso(false);
	    }

	    fichajesService.save(fichaje);
	    return fichaje;
	}
	
	@GetMapping("/estado/{usuarioId}")
	public ResponseEntity<String> obtenerEstadoFichaje(@PathVariable int usuarioId) {
	    Date hoy = new Date();
	    Fichajes fichaje = fichajesService.findFichajeDeHoy(usuarioId, hoy);

	    if (fichaje == null) {
	        return ResponseEntity.ok("pendiente");
	    } else if (fichaje.getFechaFin() != null) {
	        return ResponseEntity.ok("pendiente"); // Ya fichó salida
	    } else {
	        return ResponseEntity.ok("hecho"); // Ha fichado entrada y aún no salida
	    }
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
