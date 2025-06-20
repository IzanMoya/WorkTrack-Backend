package com.izan.backend.mvc.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.izan.backend.mvc.entities.Empresas;

public interface IEmpresasDAO extends CrudRepository<Empresas, Integer>{
	@Query("SELECT e FROM Empresas e WHERE LOWER(e.nombre) = LOWER(:nombre)")
	Empresas findByNombreIgnoreCase(@Param("nombre") String nombre);
}
