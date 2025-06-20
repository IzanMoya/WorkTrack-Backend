package com.izan.backend.mvc.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

import com.izan.backend.mvc.entities.Empresas;
import com.izan.backend.mvc.services.IEmpresasService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/worktrack/empresas")
public class EmpresasRestController {

	@Autowired
	private IEmpresasService empresasService;
	
	@GetMapping("")
	public ResponseEntity<?> getAllEmpresas() {
		List<Empresas> empresas = empresasService.findAll();
		if(!empresas.isEmpty()) {
			return new ResponseEntity<List<Empresas>>(empresas, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	@GetMapping("/nombre/{nombre}")
	public ResponseEntity<Empresas> getEmpresaByNombre(@PathVariable String nombre) {
	    Empresas empresa = empresasService.findByNombre(nombre);
	    if (empresa != null) {
	        return new ResponseEntity<>(empresa, HttpStatus.OK);
	    }
	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/{id}")
	public Empresas getEmpresaById(@PathVariable int id) {
		return empresasService.findById(id);
	}
	
	@PostMapping("")
	public ResponseEntity<?> create(@RequestBody Empresas empresa) {
	    try {
	        Empresas existente = empresasService.findByNombre(empresa.getNombre());
	        if (existente != null) {
	            return ResponseEntity.status(HttpStatus.CONFLICT).body("La empresa ya existe.");
	        }

	        empresasService.save(empresa);
	        return ResponseEntity.ok(empresa);
	    } catch (DataIntegrityViolationException ex) {
	        return ResponseEntity.status(HttpStatus.CONFLICT).body("Empresa duplicada");
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar empresa");
	    }
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteEmpresa(@PathVariable int id) {
		Empresas empresas = empresasService.findById(id);
		if(empresas == null) {
			throw new RuntimeException("Empresa con ID " + id + " no se ha encontrado");
		}
		empresasService.delete(empresas);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Empresas update(@RequestBody Empresas e, @PathVariable int id) {
		return empresasService.update(e, id);
	}
}
