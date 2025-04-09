package com.izan.backend.mvc.controllers;

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
	
	@GetMapping("/{id}")
	public Empresas getEmpresaById(@PathVariable int id) {
		return empresasService.findById(id);
	}
	
	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public Empresas create(@RequestBody Empresas empresa) {
		empresasService.save(empresa);
		return empresa;
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
