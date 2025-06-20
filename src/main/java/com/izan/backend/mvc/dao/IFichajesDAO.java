package com.izan.backend.mvc.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.izan.backend.mvc.entities.Fichajes;

public interface IFichajesDAO extends JpaRepository<Fichajes, Integer> {

	@Query("SELECT f FROM Fichajes f WHERE f.usuarios.id = :usuarioId AND FUNCTION('DATE', f.fechaInicio) = CURRENT_DATE")
	List<Fichajes> findFichajesDeHoy(@Param("usuarioId") int usuarioId);
    
    @Query("SELECT f FROM Fichajes f JOIN f.usuarios u JOIN u.empresa e WHERE e.id = :empresaId")
    List<Fichajes> findByEmpresaId(@Param("empresaId") int empresaId);
}
